package com.example.christoffer.coordinatesystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.christoffer.coordinatesystem.views.CanvasView;
import com.example.christoffer.coordinatesystem.views.PathView;

/**
 * Study about coordinate system used in computers graphics
 *
 * Coordinate system is a method of representing the position
 * of an object or a point in 2D or 3D
 *
 * In math <b>point<b/> is an abstract concept representing an exact location.
 * It has no size, only a position
 *
 * Sistema de coordenadas cartesianos cujo pixel é a sua unidade de medida é
 * chamado de "Device Coordinate system" (Sistema de coordenada de dispositivo)
 *
 * Logaical coordinate systen, is a device-independent coordinate defined by a
 * programmer for a particular task
 *
 * Local system Coordinate: Um exemplo de sistema de coordenada local é a dimensão
 * de uma janela de uma aplicação qualquer aberta por um usuario. Essa janela formada
 * por pixels tem em si um sistema de coordenada local
 *
 * Global system Coordinate: Um exemplo é a tela que abriga a janela mencionada anteriormente,
 * ou sejaa propria coordenada de dispositivo (Device coordinate system)
 *
 * */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(new PathView(this));
    }
}
