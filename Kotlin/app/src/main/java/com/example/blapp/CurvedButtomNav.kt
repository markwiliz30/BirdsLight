package com.example.blapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.jar.Attributes

class CurvedButtomNav : BottomNavigationView{

    private var mPath:Path?=null
    private var mPaint:Paint?=null

    val CURVED_CIRCLE_RADIUS =90

    var mFirstCurvedStartPoint = Point()
    var mFirstCurvedEndPoint = Point()
    var mFirstCurvedControlPoint1 = Point()
    var mFirstCurvedControlPoint2 = Point()

    var mSecondCurvedStartPoint = Point()
    var mSecondCurvedEndPoint = Point()
    var mSecondCurvedControlPoint1 = Point()
    var mSecondCurvedControlPoint2 = Point()

    var mNavigationBarWidth:Int=0
    var mNavigationBarHeight:Int=0

    constructor(context: Context): super(context)
    {
        init()
    }

    constructor(context: Context,attrs:AttributeSet):super(context,attrs)
    {
        init()
    }

    constructor(context: Context,attrs: AttributeSet,defStyleAttr:Int):super(context,attrs,defStyleAttr)
    {
        init()
    }

        private fun init(){
            mPath= Path()
            mPaint= Paint()
            mPaint!!.style= Paint.Style.FILL_AND_STROKE
            mPaint!!.color = Color.WHITE
            setBackgroundColor(Color.TRANSPARENT)
        }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mNavigationBarHeight= height
        mNavigationBarWidth= width

        mFirstCurvedStartPoint.set(mNavigationBarWidth / 2 - CURVED_CIRCLE_RADIUS * 2 - CURVED_CIRCLE_RADIUS /3, 0)

        mFirstCurvedEndPoint.set(mNavigationBarWidth / 2 , CURVED_CIRCLE_RADIUS + CURVED_CIRCLE_RADIUS /4)

        mSecondCurvedStartPoint = mFirstCurvedEndPoint

        mSecondCurvedEndPoint.set(mNavigationBarWidth / 2 + CURVED_CIRCLE_RADIUS * 2 + CURVED_CIRCLE_RADIUS / 3 , 0)

        mFirstCurvedControlPoint1.set(mFirstCurvedStartPoint.x  + CURVED_CIRCLE_RADIUS + CURVED_CIRCLE_RADIUS / 4 , mFirstCurvedStartPoint.y)

        mFirstCurvedControlPoint2.set(mFirstCurvedEndPoint.x - CURVED_CIRCLE_RADIUS * 2 + CURVED_CIRCLE_RADIUS, mFirstCurvedEndPoint.y)


        mSecondCurvedControlPoint1.set(mSecondCurvedStartPoint.x+CURVED_CIRCLE_RADIUS*2 - CURVED_CIRCLE_RADIUS , mSecondCurvedStartPoint.y)

        mSecondCurvedControlPoint2.set(mSecondCurvedEndPoint.x - (CURVED_CIRCLE_RADIUS + CURVED_CIRCLE_RADIUS / 4), mSecondCurvedEndPoint.y)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPath!!.reset()
        mPath!!.moveTo(0f,0f)
        mPath!!.lineTo(mFirstCurvedStartPoint.x.toFloat(),mFirstCurvedStartPoint.y.toFloat())

        mPath!!.cubicTo(mFirstCurvedControlPoint1.x.toFloat(),mFirstCurvedControlPoint1.y.toFloat(),mFirstCurvedControlPoint2.x.toFloat(),mFirstCurvedControlPoint2.y.toFloat()
        ,mFirstCurvedEndPoint.x.toFloat(),mFirstCurvedEndPoint.y.toFloat())

        mPath!!.cubicTo(mSecondCurvedControlPoint1.x.toFloat(),mSecondCurvedControlPoint1.y.toFloat(),
            mSecondCurvedControlPoint2.x.toFloat(),mSecondCurvedControlPoint2.x.toFloat(),
            mSecondCurvedEndPoint.x.toFloat(),mSecondCurvedEndPoint.y.toFloat())

        mPath!!.lineTo(mNavigationBarWidth.toFloat(), 0f)
        mPath!!.lineTo(mNavigationBarWidth.toFloat(),mNavigationBarHeight.toFloat())
        mPath!!.lineTo(0f,mNavigationBarHeight.toFloat())
        mPath!!.close()

        canvas.drawPath(mPath!!,mPaint!!)

    }
}