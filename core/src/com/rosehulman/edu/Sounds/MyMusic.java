package com.rosehulman.edu.Sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by suj1 on 2/8/2017.
 */

public class MyMusic {

    private Music myMusic;
    private boolean isMute;

    public MyMusic(String musicName, boolean isMute){
        if(!isMute) {
            this.myMusic = Gdx.audio.newMusic(Gdx.files.internal(musicName));
        }
        this.isMute = isMute;
    }

    public void startMusic(){
        if(!isMute) {
            this.myMusic.setLooping(true);
            this.myMusic.setVolume(0.1f);
            this.myMusic.play();
        }
    }

    public void disposeMusic(){
        if(!isMute) {
            this.myMusic.stop();
            this.myMusic.dispose();
        }
    }

    public void pauseMusic(){
        if(!isMute) {
            this.myMusic.pause();
        }
    }

    public void contiuneMusic(){
        if(!isMute) {
            this.myMusic.play();
        }
    }

}
