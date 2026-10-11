package com.jayorehla.nepobaby.game.Mouskatools;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import com.jme3.audio.AudioData;
import com.jme3.audio.AudioNode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// this'll keep the TTS and the game not to fight by being on different treads

public class VoiceSyth extends BaseAppState {

    Voice voice;
    private ExecutorService voiceExecutor;

    VoiceManager voiceManager;

    @Override
    protected void initialize(Application app) {
        // this inits the voice i think Im lowkirkgenually folloing a guide. i dont know everything...
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
       voiceManager = VoiceManager.getInstance();
       voiceExecutor = Executors.newSingleThreadExecutor();

       // for what ever reson i drink stupid juce and forgor
        if (voice != null){
            SetVoice("kevin");
        }

        if (voice == null){
            System.out.println("Voice: I'm Null!");
        }else System.out.println("Voice: I'm NOT Null!");

        if (voiceManager == null){
            System.out.println("voiceManager: I'm Null!");
        }else System.out.println("voiceManager: I'm NOT Null!");

        if (voiceExecutor == null){
            System.out.println("voiceExecutor: I'm Null!");
        }else System.out.println("voiceExecutor: I'm NOT Null!");

        System.out.println("voiceExecutor is shuttdown??" + voiceExecutor.isShutdown());


    }

    // okay so lets use Kelvin to test but in the not so far fecth furture we outa be flexable
    public void SetVoice(String voice){
        // to make sure this runns
        if (voiceManager == null) {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            voiceManager = VoiceManager.getInstance();
        }

        this.voice = voiceManager.getVoice(voice);

        if (this.voice != null) {
            this.voice.allocate();
        } else {
            System.err.println("Yo! uh '" + voice +"' isnt a real voice you can do! stupid baka...");
        }
    }

    public void SayText(String text){
        System.out.println("*clears troat to spit bars*");
        if (voice == null || voiceExecutor == null) return;
        if (voiceExecutor.isShutdown()) return;
        System.out.println("im speaking bitch!");

        voiceExecutor.submit(()-> {
                    synchronized (voice){
                        System.out.println("Now im puttinig my money wher my mouth is");
                        voice.speak(text);
                    }
                }
        );
    }

    @Override
    protected void cleanup(Application app) {
        if (voiceExecutor != null) voiceExecutor.shutdownNow();
        if (voice != null) voice.deallocate();
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
