package com.jayorehla.nepobaby.game.Mouskatools;

import com.github.thething.zam.synthesizer.SpeechSynthesizer;
import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// this'll keep the TTS and the game not to fight by being on different treads

// OH yeah do you love SAM?! I do! well some gentleman out thier made it so SAM
// can runn on Java! isnt that cool?!!!!!! if it works (haven tested yet) ill be so happy
// It was made by Martin L on Github. https://github.com/the-thing/zam hes really cool
// hopefully this puts an end to my TTS rabbit hole i went on and i could actually code

// okay i tested it (im not deltaeing my old comets who do you think im am huh?) its so cool!
// thanks Martin

public class VoiceSyth extends BaseAppState {

    Voice voice;

    MaryInterface mary;


    private ExecutorService voiceExecutor;

    VoiceManager voiceManager;

     AudioInputStream audio;

     SpeechSynthesizer sam;

     int pitch = 64, speed = 72, mouth = 128, throat = 128;

     boolean singing = false;

     public boolean ispeaking = false;

    @Override
    protected void initialize(Application app) {
        // this inits the voice i think Im lowkirkgenually folloing a guide. i dont know everything...
//        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
//        voiceManager = VoiceManager.getInstance();
        voiceExecutor = Executors.newSingleThreadExecutor();

         sam = SpeechSynthesizer.newInstance();

        // for what ever reson i drink stupid juce and forgor
//        if (voice != null){
//            SetVoice("kevin");
//        }

//        if (voice == null){
//            System.out.println("Voice: I'm Null!");
//        }else System.out.println("Voice: I'm NOT Null!");
//
        if (sam == null){
            System.out.println("sam: I'm NOT Null! (i lied)");
        }else System.out.println("sam: I'm NOT Null!");

        if (voiceExecutor == null){
            System.out.println("voiceExecutor: I'm Null!");
        }else System.out.println("voiceExecutor: I'm NOT Null!");

        System.out.println("voiceExecutor is shuttdown??" + voiceExecutor.isShutdown());


    }

    // okay so lets use Kelvin to test but in the not so far fecth furture we outa be flexable
    public void SetVoice(String voice){
        return;
//        // to make sure this runns
//        if (voiceManager == null) {
//            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
//            voiceManager = VoiceManager.getInstance();
//        }
//
//        this.voice = voiceManager.getVoice(voice);
//
//        if (this.voice != null) {
//            this.voice.allocate();
//        } else {
//            System.err.println("Yo! uh '" + voice +"' isnt a real voice you can do! stupid baka...");
//        }
    }

    public void SetVoice(int pitch, int speed, int throat, int mouth, boolean singing){
        // to make sure this runns


        this.pitch = pitch;
        this.speed = speed;
        this.throat = throat;
        this.mouth = mouth;
        this.singing = singing;

//        if (voiceManager == null) {
//            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
//
//            voiceManager = VoiceManager.getInstance();
//        }
//
//        System.setProperty("com.sun.speech.freetts.voice.defaultPitch", String.valueOf(pitch));
//        System.setProperty("com.sun.speech.freetts.voice.defaultRate", String.valueOf(rate));
//        System.setProperty("com.sun.speech.freetts.voice.defaultPitchRange", String.valueOf(pitchRange));
//
//        this.voice = voiceManager.getVoice(voice);
//
//        if (this.voice != null) {
//            this.voice.setPitch(pitch);
//            this.voice.setRate(rate);
//            this.voice.setPitchRange(pitchRange);
//            this.voice.allocate();
//        } else {
//            System.err.println("Yo! uh '" + voice +"' isnt a real voice you can do! stupid baka...");
//        }
    }

    public void SayText(String text) {
        System.out.println(text);
        if (sam == null || voiceExecutor == null) return;
        if (voiceExecutor.isShutdown()) return;
        ispeaking = true;

        voiceExecutor.submit(() -> {
                    System.out.println("Im speaking know!");
                    try {
                        sam.say(text, speed, pitch, mouth, throat, singing);
                    } catch (LineUnavailableException e) {
                        throw new RuntimeException(e);
                    }finally {
                        ispeaking = false;
                    }
            System.out.println("done owo");
        }
        );
    }

    @Override
    protected void cleanup(Application app) {
        if (voiceExecutor != null) voiceExecutor.shutdownNow();
//        if (voice != null) voice.deallocate();
        mary = null;
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
