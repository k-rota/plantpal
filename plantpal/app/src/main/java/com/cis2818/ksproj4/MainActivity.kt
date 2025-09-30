package com.cis2818.ksproj4

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    // plant list
    private val plants = mutableListOf<Plant>()

    // adapter reference
    private lateinit var adapter: PlantAdapter

    // launcher to handle Add Plant form results
    private val addLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val plant = result.data?.getParcelableExtra<Plant>("plant")
            plant?.let {
                plants.add(it) // always add, never replace
                adapter.submit(plants)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // toolbar setup
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // recycler setup
        val recycler = findViewById<RecyclerView>(R.id.plantList)
        adapter = PlantAdapter(
            onLongDelete = { plant -> plants.remove(plant)
                adapter.submit(plants)
            }
        )
        recycler.adapter = adapter

        // floating action button to add plant
        val fab = findViewById<FloatingActionButton>(R.id.fabAdd)
        fab.setOnClickListener {
            val intent = Intent(this, PlantFormActivity::class.java)
            addLauncher.launch(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
