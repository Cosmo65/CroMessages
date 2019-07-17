package br.programacaodinamica.cromessages

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.programacaodinamica.cromessages.fragments.SeekBarControlFragment
import br.programacaodinamica.cromessages.fragments.TextControlFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_color_selection.*

class MainActivity : AppCompatActivity(){

    // falta sincronização dos dados e interface
    private var red = 100
    private var green = 100
    private var blue = 100
    private lateinit var colorViewModel: ColorViewModel
    private val fragmentsMap = mapOf(
        Pair(R.id.text_menu_item, TextControlFragment()),
        Pair(R.id.bars_menu_item, SeekBarControlFragment())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        colorViewModel = ViewModelProviders.of(this).get(ColorViewModel::class.java)
        setUpListeners()
        subscribe()
    }

    private fun setUpListeners(){

        bottom_navigationview.setOnNavigationItemSelectedListener {
            val fragment = fragmentsMap[it.itemId]
            fragment?.replaceOn(R.id.control_container)
            true
        }
        bottom_navigationview.selectedItemId = R.id.text_menu_item
    }

    private fun subscribe(){
    colorViewModel.color.observe(this, Observer {
        color_card.setCardBackgroundColor(it)
    })

    }

    private fun Fragment.replaceOn(containerId: Int){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, this)
        transaction.commit()
    }
}
