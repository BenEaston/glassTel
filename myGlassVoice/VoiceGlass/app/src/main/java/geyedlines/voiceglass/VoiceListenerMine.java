package geyedlines.voiceglass;

import android.os.Bundle;
import android.speech.RecognitionListener;

import com.google.glass.logging.Log;

/**
 * Created by beneaston on 07/11/2015.
 */
public class VoiceListenerMine implements RecognitionListener {
    private static final String TAG = "Recognition Listener";
    @Override
    public void onBeginningOfSpeech() {
        Log.i(TAG, "begin");
    }

    @Override
    public void onEndOfSpeech() {
        Log.i(TAG, "end");
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(TAG, "buffer");
    }

    @Override
    public void onError(int error) {
        Log.e(TAG, "error");
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        Log.i(TAG, "event");
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        Log.i(TAG, "partial results");
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        Log.i(TAG, "ready for speech");
    }

    @Override
    public void onResults(Bundle results) {
        Log.i(TAG, "Got RESULTS!");
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(TAG, "rmsChange");
    }
}