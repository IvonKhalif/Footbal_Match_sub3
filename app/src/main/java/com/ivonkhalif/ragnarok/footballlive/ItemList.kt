package com.ivonkhalif.ragnarok.footballlive

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout

class ItemList : AnkoComponent<ViewGroup> {
    @SuppressLint("SetTextI18n")
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        cardView {
            lparams(matchParent, wrapContent) { margin = dip(5) }
            radius = 10f

            linearLayout {
                lparams(matchParent, wrapContent)
                orientation = LinearLayout.VERTICAL
                padding = dip(10)
                gravity = Gravity.CENTER_HORIZONTAL


                textView {
                    id = R.id.Id_date
                    text = "Date TIme"
                    setTypeface(Typeface.DEFAULT_BOLD)
                    textColor = Color.BLUE
                }.lparams(wrapContent, wrapContent) { margin = dip(5) }

                linearLayout {
                    lparams(wrapContent, wrapContent)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

                    linearLayout {
                        lparams(wrapContent, wrapContent){rightMargin = dip(10)}
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER

                        textView {
                            id = R.id.Id_ClubHome
                            text = "Barcelona FC"
                            setTypeface(Typeface.DEFAULT_BOLD)
                            textColor = Color.BLACK
                        }.lparams(wrapContent, wrapContent) { bottomMargin = dip(4) }

                        textView {
                            id = R.id.Id_HomeScore
                            text = "0"
                            setTypeface(typeface, Typeface.BOLD_ITALIC)
                            textColor = Color.BLACK
                        }.lparams(wrapContent, wrapContent) { topMargin = dip(4) }
                    }

                    linearLayout {
                        lparams(wrapContent, wrapContent)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER

                        textView {
                            //                        id = R.id.Id_club
                            text = "vs"
                            setTypeface(Typeface.DEFAULT_BOLD)
                            textColor = Color.BLACK
                        }.lparams(wrapContent, wrapContent) { bottomMargin = dip(4) }
                    }

                    linearLayout {
                        lparams(wrapContent, wrapContent){leftMargin= dip(10)}
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER

                        textView {
                            id = R.id.Id_ClubAway
                            text = "Barcelona FC"
                            setTypeface(Typeface.DEFAULT_BOLD)
                            textColor = Color.BLACK
                        }.lparams(wrapContent, wrapContent) { bottomMargin = dip(4) }

                        textView {
                            id = R.id.Id_AwayScore
                            text = "0"
                            setTypeface(typeface, Typeface.BOLD_ITALIC)
                            textColor = Color.BLACK
                        }.lparams(wrapContent, wrapContent) { topMargin = dip(4) }
                    }
                }
            }
        }
    }
}