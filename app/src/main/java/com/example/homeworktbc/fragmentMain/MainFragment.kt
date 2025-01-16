package com.example.homeworktbc.fragmentMain

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktbc.MainAdapter
import com.example.homeworktbc.baseClass.BaseFragment
import com.example.homeworktbc.databinding.FragmentMainBinding
import com.example.homeworktbc.json.serverResponse
import com.example.homeworktbc.models.FieldItem
import kotlinx.serialization.json.Json

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private lateinit var fields: List<List<FieldItem>>

    override fun start() {
        fields = Json.decodeFromString(serverResponse)

        fields.forEachIndexed { groupIndex, group ->
            println("Group $groupIndex:")
            group.forEach { field ->
                println("  Field ID: ${field.fieldId}, Hint: ${field.hint}, Type: ${field.fieldType}")
            }
        }

        val adapter = MainAdapter { fieldId, text ->
            println("Field $fieldId changed to $text")
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }


    }
}


