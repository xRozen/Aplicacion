package com.example.aplicacion;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {

    private Paint paint; // Objeto Paint para definir el estilo de los trazos (color, grosor)
    private Path path;   // Objeto Path para almacenar la geometría de los trazos

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK); // Color inicial del pincel
        paint.setStyle(Paint.Style.STROKE); // Estilo de trazo (solo contorno)
        paint.setStrokeWidth(10); // Grosor del trazo
        path = new Path(); // Inicializa el objeto Path para los trazos
    }

    /**
     * Establece el color actual del pincel.
     * Después de cambiar el color, la vista se invalida para forzar un redibujado,
     * lo que hará que todos los trazos existentes se muestren con el nuevo color.
     *
     * @param color El nuevo color (ej. Color.RED, Color.BLUE).
     */
    public void setPaintColor(int color) {
        paint.setColor(color);
        invalidate(); // Solicita un redibujado de la vista. Esto hará que onDraw se llame.
    }

    /**
     * Borra todos los trazos en la pantalla.
     * Reinicia el objeto Path y solicita un redibujado.
     */
    public void clearDrawing() {
        path.reset(); // Elimina todos los segmentos del Path, dejando el lienzo en blanco.
        invalidate(); // Solicita un redibujado de la vista para mostrar el lienzo vacío.
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Dibuja todo el Path acumulado usando la configuración actual del objeto Paint.
        // Si el color del Paint ha cambiado, todos los trazos en el Path se dibujarán con el nuevo color.
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Cuando el usuario toca la pantalla por primera vez,
                // mueve el punto de inicio del trazo a la posición actual.
                path.moveTo(x, y);
                return true; // Indica que hemos manejado este evento.
            case MotionEvent.ACTION_MOVE:
                // Mientras el usuario arrastra el dedo,
                // añade un segmento de línea al Path desde el último punto.
                path.lineTo(x, y);
                break; // Continúa procesando el evento.
        }

        invalidate(); // Solicita un redibujado de la vista para mostrar el trazo actualizado.
        return true; // Indica que hemos manejado este evento.
    }
}
