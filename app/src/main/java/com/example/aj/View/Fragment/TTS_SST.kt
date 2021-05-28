package com.example.aj

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import java.util.*

class TTS_STT : Fragment(), TextToSpeech.OnInitListener{
      lateinit var btn:ImageButton
      lateinit var editText1:EditText
      lateinit var e11:EditText
      lateinit var b11:ImageView
      lateinit var tts: TextToSpeech



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_t_t_s__s_s_t, container, false)

        btn =view!!.findViewById(R.id.btSpeech)
      editText1=view!!.findViewById(R.id.editText)
       e11 =view!!.findViewById(R.id.e1)
         b11 = view!!.findViewById(R.id.b1)


        var RecordAudioRequestCode = 1
//permission for recording audio
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(
                context!! as Activity,
                arrayOf(Manifest.permission.RECORD_AUDIO), RecordAudioRequestCode
            );
        }

        startSpeechToText()
        tts = TextToSpeech(getActivity(), this)

        b11.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                speakOut();

            }
        })
        return view

    }


    @SuppressLint("ClickableViewAccessibility")
    private fun startSpeechToText() {
//initializing speech recognizer
        val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(bundle: Bundle) {}

            override fun onBeginningOfSpeech() {}

            override fun onRmsChanged(v: Float) {}

            override fun onBufferReceived(bytes: ByteArray) {}

            override fun onEndOfSpeech() {}

            override fun onError(i: Int) {}

            override fun onResults(bundle: Bundle) {
                val matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)//getting all the matches
                //displaying the first match
                if (matches != null)
                    editText1.setText(matches[0])
            }

            override fun onPartialResults(bundle: Bundle) {}

            override fun onEvent(i: Int, bundle: Bundle) {}
        })

        btn.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    speechRecognizer.stopListening()
                    editText1.hint = getString(R.string.text_hint)
                }

                MotionEvent.ACTION_DOWN -> {
                    speechRecognizer.startListening(speechRecognizerIntent)
                    editText1.setText("")
                    editText1.hint = "Listening..."
                }
            }
            false
        })
    }

    private fun speakOut() {
        e11.setText("")
        val text: String = e11.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }



    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {

            val result: Int = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                || result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                Log.e("TTS", "Language is not supported");
            } else {
                b11.setEnabled(true);
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed");
        }
    }

}