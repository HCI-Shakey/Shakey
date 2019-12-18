package com.hci.shakey.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hci.shakey.DialActivity;
import com.hci.shakey.FlashLightActivity;
import com.hci.shakey.GlobalIdentifiers;
import com.hci.shakey.MusicActivity;
import com.hci.shakey.R;
import com.hci.shakey.ShakeyFloatActivity;
import com.hci.shakey.support.LocalDataBase;
import com.hci.shakey.utils.JsonParser;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VoiceAssistantFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VoiceAssistantFragment} factory method to
 * create an instance of this fragment.
 */
public class VoiceAssistantFragment extends Fragment implements View.OnClickListener {

    private static String TAG = VoiceAssistantFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;
    private EditText mResultText;
    //private Button startBtn;
    private Toast mToast;
    private ImageView mIV;
    private String resultType = "json";
    private StringBuffer buffer = new StringBuffer();
    private boolean cyclic = false;//音频流识别是否循环调用

    private SpeechRecognizer mIat;
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    public VoiceAssistantFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToast = Toast.makeText(this.getActivity(), "", Toast.LENGTH_SHORT);
        mIat = SpeechRecognizer.createRecognizer(VoiceAssistantFragment.this.getActivity(), mInitListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_voice_assistant, container, false);
        mResultText = v.findViewById(R.id.text_result);
//        startBtn = v.findViewById(R.id.start_btn);
//        startBtn.setOnClickListener(this);
        mIV = v.findViewById(R.id.microphone);
        mIV.getDrawable().setLevel(5400);

        callBackValue.sendMessageValue("beginListen");

        return v;
    }
    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败，错误码：" + code+",请点击网址https://www.xfyun.cn/document/error-code查询解决方案");
            }
        }
    };

    /**
     * 听写监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
            showTip("我在听");
        }

        @Override
        public void onError(SpeechError error) {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。

            showTip(error.getPlainDescription(true));

        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            showTip("结束说话");
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d(TAG, results.getResultString());
            if (resultType.equals("json")) {

                printResult(results);

            }else if(resultType.equals("plain")) {
                buffer.append(results.getResultString());
                mResultText.setText(buffer.toString());
                mResultText.setSelection(mResultText.length());
            }

            if (isLast & cyclic) {
                // TODO 最后的结果
                Message message = Message.obtain();
                message.what = 0x001;
            }
        }

        private void printResult(RecognizerResult results) {
            String text = JsonParser.parseIatResult(results.getResultString());
            String sn = null;
            // 读取json结果中的sn字段
            try {
                JSONObject resultJson = new JSONObject(results.getResultString());
                sn = resultJson.optString("sn");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mIatResults.put(sn, text);

            StringBuffer resultBuffer = new StringBuffer();
            for (String key : mIatResults.keySet()) {
                resultBuffer.append(mIatResults.get(key));
            }

            mResultText.setText(resultBuffer.toString());
            mResultText.setSelection(mResultText.length());

            commandInspect(resultBuffer.toString());
        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            // 音量改变说明有语音输入
            if (volume > 0) {
                callBackValue.sendMessageValue("hasSoundAction");
            }
            showTip("当前正在说话，音量大小：" + volume);
            Log.d(TAG, "返回音频数据："+data.length);
            mIV.getDrawable().setLevel((int)(((double)(volume))*10000/20));
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };

    int ret = 0;

    @Override
    public void onClick(View view) {
        if( null == mIat ){
            // 创建单例失败，与 21001 错误为同样原因，参考 http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=9688
            this.showTip( "创建对象失败，请确认 libmsc.so 放置正确，且有调用 createUtility 进行初始化" );
            return;
        }
//        switch (view.getId()) {
//            case R.id.start_btn :
//                buffer.setLength(0);
//                mResultText.setText(null);// 清空显示内容
//                mIatResults.clear();
//                setParam();
//                // 不显示听写对话框
//                if (mIat.isListening()) mIat.stopListening();
//                ret = mIat.startListening(mRecognizerListener);
//                if (ret != ErrorCode.SUCCESS) {
//                    showTip("听写失败,错误码：" + ret+",请点击网址https://www.xfyun.cn/document/error-code查询解决方案");
//                } else {
//                    showTip("开始听写");
//                }
//                break;
//        }
    }

    public void startListening() {
        if( null == mIat ){
            // 创建单例失败，与 21001 错误为同样原因，参考 http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=9688
            this.showTip( "创建对象失败，请确认 libmsc.so 放置正确，且有调用 createUtility 进行初始化" );
            return;
        }
        buffer.setLength(0);
        //mResultText.setText(null);// 清空显示内容
        mIatResults.clear();
        setParam();
        // 不显示听写对话框
        if (mIat.isListening()) mIat.stopListening();
        ret = mIat.startListening(mRecognizerListener);
        if (ret != ErrorCode.SUCCESS) {
            showTip("听写失败,错误码：" + ret+",请点击网址https://www.xfyun.cn/document/error-code查询解决方案");
        } else {
            showTip("开始听写");
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        callBackValue = (CallBackValue) getActivity();

    }

    private CallBackValue callBackValue;
    //定义一个接口，向父activity传递信息。让“下一步”按钮可以被点击
    public interface CallBackValue{
        void sendMessageValue(String message);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void showTip(final String str) {
        mToast.setText(str);
        mToast.show();
    }

    /**
     * 参数设置
     *
     * @return
     */
    public void setParam() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);

        mIat.setParameter(SpeechConstant.PARAMS, "iat");      //应用领域
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn"); //语音
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin"); //普通话
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);//引擎
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");//返回结果格式
        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, "4000");
        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS,"1000");
        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, "0");
    }

    // 检查语音输入结果中是否有命令词
    private void commandInspect(String curstr) {
        if (curstr.contains("听音乐")) {
            Intent intent = new Intent(this.getActivity(), MusicActivity.class);
            startActivity(intent);
            this.getActivity().finish();
            GlobalIdentifiers.Shakey_float = false;
        } else if (curstr.contains("打电话")) {
            Intent intent = new Intent(this.getActivity(), DialActivity.class);
            startActivity(intent);
            this.getActivity().finish();
            GlobalIdentifiers.Shakey_float = false;
        } else if (curstr.contains("打开手电筒")) {
            Intent intent = new Intent(this.getActivity(), FlashLightActivity.class);
            startActivity(intent);
            this.getActivity().finish();
            GlobalIdentifiers.Shakey_float = false;
        }
    }
}
