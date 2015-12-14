package com.example.christoffer.coordinatesystem.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by christoffer on 13/12/15.
 *
 * The Path class represents a series of straight line segments, curves or other drawing primitives
 * A path can be open or closed. Create of path and drawing a pth are tow separate operations
 *
 * path.moveTo(x,y);
 * path.lineTo(x,y);
 *
 * drawing
 * canvas.drawPath(path, paint)
 *
 */
public class PathView extends View {

    private Paint paint;
    private Path path;
    // private int widthView, heightView;
    private int lastPosX, lastPosY;

    public PathView(Context context) {
        super(context);
        initPaint();
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        setBackgroundColor(Color.WHITE);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        /*
        * Estilo da caneta de desenho
        * FILL: preenche a area contida dentro do caminho fechado ou
        * de um elemento primitivo da API como um circulo ou retangulo
        *
        * STROKE: desenha somente as arestas, sem preenchimeto
        * draw line with specified stroke width (traçado com largura especifica)
        *
        * FILL_AND_STROKE: Preenche a area do elemento e faz os traços do mesmo, usando
        * a mesma cor.Esse estilo quase não eh usado.
        * */
        //paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.BEVEL);
    }


    private void addPath() {
        path = new Path();
        // define o ponto(x, y) inicial do caminho que sera desenhado
        // eh como se move-se uma caneta sobre o papel e parece na posição
        // onde se quer desenhar
        path.moveTo(getLastPosX() * 0.2f, getLastPosY() * 0.2f);
        // adiciona uma linha a partir do ultimo ponto definido
        // Desenha a linha a partir do ponto definido em moveTo(x,y)
        // ao final do desenho, o proximo ponto de moveTo(x,y) eh o final de lineTo
        path.lineTo(getLastPosX() * 0.8f, getLastPosY() * 0.8f);
        setLastPosX((int) (getLastPosX() * 0.8f));
        setLastPosY((int) (getLastPosY() * 0.8f));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // como preencher o elemento desenhado de uma cor
        // e desenhar suas arestas de outra
        // FILL
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawPath(path, paint);
        // STROKE: traçado
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.YELLOW);
        canvas.drawPath(path, paint);
    }

    // onSizeChange eh executado primeiro que onDraw
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        w -= (getPaddingLeft() + getPaddingRight());
        h -= (getPaddingTop() + getPaddingBottom());
        setLastPosX(w);
        setLastPosY(h);
        // largura das linhas ao desenhar
        paint.setStrokeWidth(Math.min(w, h) * 0.02f);
        // addPath();
        // paint.setColor(Color.RED);
        // addPath();
        drawDiamond();
    }

    public int getLastPosX() {
        return lastPosX;
    }

    public void setLastPosX(int lastPosX) {
        this.lastPosX = lastPosX;
    }

    public int getLastPosY() {
        return lastPosY;
    }

    public void setLastPosY(int lastPosY) {
        this.lastPosY = lastPosY;
    }

    /*
    * Testando o uso do atributo 'Join Styles'
    *
    * Line join style control the type of corner that is
    * created when two lines meet.
    * Three style: {Join.BEVEL, Join,MITER, Join.ROUND}
    * Join.BEVEL: A filled triangle connects two lines, creating a beveled corner
    * (beveled corner == canto recordado)
    *
    * Join.MITER: Create a sharp or pointed corner
    * (sharp corner == curva afinada, quina. Sharp == afiado)
    *
    * Join.ROUND: A folledarc connects 2 lines, creating a rounded corner
    * */
    private void drawTriangle() {
        path = new Path();
        path.moveTo(getLastPosX() * 0.5f, getLastPosY() * 0.2f);
        path.lineTo(getLastPosX() * 0.8f, getLastPosY() * 0.8f);
        path.lineTo(getLastPosX() * 0.2f, getLastPosY() * 0.8f);
        // If the last point of a path is noit equal to the first point
        // a line segment is automatically added
        path.close();
    }

    private void drawDiamond() {
        path = new Path();
        // ponto inicial
        path.moveTo(getLastPosX() * 0.5f, getLastPosY() * 0.2f);
        // aresta da direita sup, começa x = 80% da largura, y = 50% da altura (Tela)
        path.lineTo(getLastPosX() * 0.8f, getLastPosY() * 0.5f);
        // aresta da direita inf, começa x = 50% da largura, y = 80% da altura (Tela)
        // porem a partir do moveTo() que ja comeca a partir dos 20%, entao
        // a altura desse ponto eh de 60% da altura da tela
        path.lineTo(getLastPosX() * 0.5f, getLastPosY() * 0.8f);
        // aresta da esquerda inf, começa x = 20% da largura, y = 50% da altura (Tela)
        path.lineTo(getLastPosX() * 0.2f, getLastPosY() * 0.5f);
        // ponto inicial
        path.lineTo(getLastPosX() * 0.5f, getLastPosY() * 0.2f);
        // if I do not close a path, the "join style" will not be applied
        // to the starting and end points of a path
        path.close();
    }
}
