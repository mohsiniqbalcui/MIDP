package com.app.midp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PieChartView extends View {
    private int[] data = {25, 25, 25, 25};
    private final int[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA};
    private final Paint paint = new Paint();

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setData(int[] values) {
        this.data = values;
        invalidate(); // Redraw the view
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int sum = 0;
        for (int value : data) sum += value;
        if (sum == 0) return;

        int width = getWidth();
        int height = getHeight();
        int min = Math.min(width, height);
        int left = (width - min) / 2;
        int top = (height - min) / 2;

        float startAngle = 0;
        for (int i = 0; i < data.length; i++) {
            float sweepAngle = (360f * data[i]) / sum;
            paint.setColor(colors[i % colors.length]);
            canvas.drawArc(left, top, left + min, top + min, startAngle, sweepAngle, true, paint);
            startAngle += sweepAngle;
        }
    }
}
