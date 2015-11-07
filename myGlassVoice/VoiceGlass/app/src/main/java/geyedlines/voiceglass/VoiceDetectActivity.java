package geyedlines.voiceglass;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;
import com.google.glass.input.VoiceInputHelper;
import com.google.glass.input.VoiceListener;
import com.google.glass.logging.FormattingLogger;
import com.google.glass.logging.FormattingLoggers;
import com.google.glass.logging.Log;
import com.google.glass.voice.VoiceCommand;
import com.google.glass.voice.VoiceConfig;


/**
 * An {@link Activity} showing a tuggable "Hello World!" card.
 * <p/>
 * The main content view is composed of a one-card {@link CardScrollView} that provides tugging
 * feedback to the user when swipe gestures are detected.
 * If your Glassware intends to intercept swipe gestures, you should set the content view directly
 * and use a {@link com.google.android.glass.touchpad.GestureDetector}.
 *
 * @see <a href="https://developers.google.com/glass/develop/gdk/touch">GDK Developer Guide</a>
 */
public class VoiceDetectActivity extends Activity {

    private VoiceInputHelper mVoiceInputHelper;
    private VoiceConfig mVoiceConfig;

    private CardScrollView mCardScroller;
    private static final int SPEECH_REQUEST = 0;

    private View mView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.voice_activity);
    createView("hello");
        Log.i("ht", "test");
        String[] items = {"red", "green", "blue", "orange"};
        mVoiceConfig = new VoiceConfig( items);
        mVoiceInputHelper = new VoiceInputHelper(this, new MyVoiceListener(mVoiceConfig));
        //VoiceInputHelper.VoiceCommandEvent(mVoiceInputHelper);
        mVoiceInputHelper.newUserActivityObserver(this);

        //mVoiceInputHelper.
    }

    @Override
    protected void onResume() {
        super.onResume();
      //  mVoiceInputHelper.addVoiceServiceListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mVoiceInputHelper.removeVoiceServiceListener();
    }

    private void createView(String text){
        mView = buildView(text);

        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(new CardScrollAdapter() {
            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public Object getItem(int position) {
                return mView;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return mView;
            }

            @Override
            public int getPosition(Object item) {
                if (mView.equals(item)) {
                    return 0;
                }
                return AdapterView.INVALID_POSITION;
            }
        });
        // Handle the TAP event.
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Plays disallowed sound to indicate that TAP actions are not supported.
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                am.playSoundEffect(Sounds.DISALLOWED);
            }
        });
        setContentView(mCardScroller);
    }

    private View buildView(String text) {
        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.TEXT);

        card.setText(text);
        return card.getView();
    }


    public class MyVoiceListener implements VoiceListener {
        protected final VoiceConfig voiceConfig;

        public MyVoiceListener(VoiceConfig voiceConfig) {
            this.voiceConfig = voiceConfig;
        }

        //@Override
        public void onVoiceServiceConnected() {
            mVoiceInputHelper.setVoiceConfig(mVoiceConfig);
        }

       // @Override
        public void onVoiceServiceDisconnected() {

        }

        @Override
        public VoiceConfig onVoiceCommand(VoiceCommand vc) {
            String recognizedStr = vc.getLiteral();
            Log.i("VoiceActivity", "Recognized text: " + recognizedStr);

            return voiceConfig;
        }

        @Override
        public FormattingLogger getLogger() {
            return FormattingLoggers.getContextLogger();
        }

        @Override
        public boolean isRunning() {
            return true;
        }

        @Override
        public boolean onResampledAudioData(byte[] arg0, int arg1, int arg2) {
            return false;
        }

        //@Override
        public boolean onVoiceAmplitudeChanged(double arg0) {
            return false;
        }

        @Override
        public void onVoiceConfigChanged(VoiceConfig arg0, boolean arg1) {

        }
    }
}