package com.example.christoffer.coordinatesystem.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by christoffer on 13/12/15.
 */
public class CanvasView extends View {
    private Paint paint;
    private int widthView, heightView;
    public CanvasView(Context context) {
        super(context);
        initPaint();
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        widthView  = getWidth() - getPaddingLeft() - getPaddingRight();
        heightView = getHeight() - getPaddingTop() - getPaddingBottom();

        /**
         * Lines can have one of three cap styles: {butt, round, square}
         *
         * Traduação de Cap: capa. para esse contexto
         * cap seria uma cobertura adicionada no inicio e no fim da linha
         * por exemplo:QUando usadno Cap.Round, no começo da linha e ao final dela
         * eh adicionado um semi circulo (======) tornando a borda da linha arredondada
         *
         * Thre three caps style are defined in Paint.Cap enum
         * Cap.BUTT: A flat edge is put perpendicular to each end of the line with no cap added
         * this is default cap style
         *
         * Cap.ROUND: A semicircle cap is added to each end of the line. The cap diameter is
         * equals to width of the line
         *
         * Cap.SQUARE: A square end cap is added to each end of the line.
         * */
        canvas.drawLine(widthView * 0.2f, heightView * 0.2f
                ,widthView * 0.8f, heightView * 0.8f, paint);
    }

    private void initPaint() {
        setBackgroundColor(Color.LTGRAY);
        // A flag ANTI_ALIAS_FLAG eh usada para
        // fazer linhas mais suaveizadas
        this.paint  = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // isso faz com que a largura da linha seja de 10% da larguda
        // da tela, independente da posicao que ela esteja, horizontal o vertical
        paint.setStrokeWidth(Math.max(w,h) * 0.05f);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public Paint getPaint() {
        return paint;
    }

    public int getWidthView() {
        return widthView;
    }

    public int getHeightView() {
        return heightView;
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }
}
