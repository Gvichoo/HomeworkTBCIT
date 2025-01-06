package com.example.homeworktbc

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.Toast
import com.example.homeworktbc.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    private lateinit var viewBinding: FragmentGameBinding
    private lateinit var gridLayout: GridLayout
    private lateinit var resetButton: Button
    private var gridSize = 3
    private var currentPlayer = "X"
    private val board = mutableListOf<MutableList<String>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentGameBinding.inflate(inflater, container, false)
        gridLayout = viewBinding.gridLayout
        resetButton = viewBinding.btnResetGame
        gridSize = arguments?.getInt("Size") ?: 3
        initializeBoard()
        createGameGrid()
        resetButton.setOnClickListener {
            resetGame()
        }
        return viewBinding.root
    }

    private fun initializeBoard() {
        for (i in 0 until gridSize) {
            val row = mutableListOf<String>()
            for (y in 0 until gridSize) {
                row.add("")
            }
            board.add(row)
        }
    }

    private fun createGameGrid() {
        gridLayout.rowCount = gridSize
        gridLayout.columnCount = gridSize
        gridLayout.removeAllViews()

        for (i in 0 until gridSize) {
            for (y in 0 until gridSize) {
                val button = ImageButton(requireContext())
                button.layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 0
                    columnSpec = GridLayout.spec(y, 1f)
                    rowSpec = GridLayout.spec(i, 1f)
                }
                button.setImageResource(R.drawable.ic_launcher_foreground)
                button.setOnClickListener {
                    onCellClicked(i, y, button)
                }
                gridLayout.addView(button)
            }
        }
    }

    private fun onCellClicked(row: Int, col: Int, button: ImageButton) {
        if (board[row][col].isNotEmpty()) {
            return
        }
        board[row][col] = currentPlayer
        button.setImageResource(
            if (currentPlayer == "X"){
                R.drawable.iksi
            } else{
                R.drawable.circle
            }
        )

        if (checkWinner()) {
            Toast.makeText(context, "$currentPlayer Wins!", Toast.LENGTH_SHORT).show()
            showResetButton()
        } else if (isDraw()) {
            Toast.makeText(context, "It's a Draw!", Toast.LENGTH_SHORT).show()
            showResetButton()
        } else {
            currentPlayer = if (currentPlayer == "X") "O" else "X"
        }
    }

    private fun checkWinner(): Boolean {
        for (i in 0 until gridSize) {
            if (board[i].all { it == currentPlayer }){
                return true
            }
            if (board.all { it[i] == currentPlayer }) {
                return true
            }
        }
        if (board.indices.all { board[it][it] == currentPlayer }) {
            return true
        }
        if (board.indices.all { board[it][gridSize - 1 - it] == currentPlayer }){
            return true
        }
        return false
    }

    private fun isDraw(): Boolean {
        return board.all { it.all { i -> i.isNotEmpty() } }
    }

    private fun showResetButton() {
        resetButton.visibility = View.VISIBLE
    }

    private fun resetGame() {
        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                board[i][j] = ""
            }
        }
        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as ImageButton
            button.setImageResource(R.drawable.ic_launcher_foreground)
        }
        resetButton.visibility = View.GONE
        currentPlayer = "X"
    }
}

