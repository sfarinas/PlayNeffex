package com.example.playneffex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    // Atributos
    private MediaPlayer mediaPlayer;
    private SeekBar seekVolume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.nef1);
        inicilizarSeekBar();

    }

    //metodos
    private void inicilizarSeekBar(){
        seekVolume = findViewById(R.id.seekVolume);

        //configuracoes de volume do usuario.
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Recuperar os Valores de volume maximo e o atual
        int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volumeAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //configura os valores maximo para o seekbar
        seekVolume.setMax( volumeMaximo );

        //configura o valor atual do seekbar
        seekVolume.setProgress( volumeAtual );

        seekVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        /*
        seekVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
         */


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if( mediaPlayer != null && mediaPlayer.isPlaying() ){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void executarSom(View view){
        if (mediaPlayer != null){
            mediaPlayer.start();
        }

    }

    public void pausaMusica(View view){
        if (mediaPlayer.isPlaying() ){
            mediaPlayer.pause();
        }
    }

    public void paraMusica(View view){
        if ( mediaPlayer.isPlaying() ){
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.nef1);
        }
    }

}