package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tictactoe.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var gameManager: GameManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gameManager = GameManager()


        binding.one.setOnClickListener { onBoxClicked(binding.one, Position(0, 0)) }
        binding.two.setOnClickListener { onBoxClicked(binding.two, Position(0, 1)) }
        binding.three.setOnClickListener { onBoxClicked(binding.three, Position(0, 2)) }
        binding.four.setOnClickListener { onBoxClicked(binding.four, Position(1, 0)) }
        binding.five.setOnClickListener { onBoxClicked(binding.five, Position(1, 1)) }
        binding.six.setOnClickListener { onBoxClicked(binding.six, Position(1, 2)) }
        binding.seven.setOnClickListener { onBoxClicked(binding.seven, Position(2, 0)) }
        binding.eight.setOnClickListener { onBoxClicked(binding.eight, Position(2, 1)) }
        binding.nine.setOnClickListener { onBoxClicked(binding.nine, Position(2, 2)) }

        binding.startNew.setOnClickListener {
            binding.startNew.visibility = View.GONE
            gameManager.reset()
            resetBoxes()
        }


        updatePoints()

      


    }

    private fun updatePoints() {
        binding.playerOne.text = "Player X Points: ${gameManager.player1Points}"
        binding.PlayerTwo.text = "Player O Points: ${gameManager.player2Points}"
    }

    private fun resetBoxes() {
        binding.one.text = ""
        binding.two.text = ""
        binding.three.text = ""
        binding.four.text = ""
        binding.five.text = ""
        binding.six.text = ""
        binding.seven.text = ""
        binding.eight.text = ""
        binding.nine.text = ""
        binding.one.background = null
        binding.two.background = null
        binding.three.background = null
        binding.four.background = null
        binding.five.background = null
        binding.six.background = null
        binding.seven.background = null
        binding.eight.background = null
        binding.nine.background = null
        binding.one.isEnabled = true
        binding.two.isEnabled = true
        binding.three.isEnabled = true
        binding.four.isEnabled = true
        binding.five.isEnabled = true
        binding.six.isEnabled = true
        binding.seven.isEnabled = true
        binding.eight.isEnabled = true
        binding.nine.isEnabled = true
    }


    private fun onBoxClicked(box: TextView, position: Position) {
        if (box.text.isEmpty()) {
            box.text = gameManager.currentPlayerMark
            val winningLine = gameManager.makeMove(position)
            if (winningLine != null) {
                updatePoints()
                disableBoxes()
                binding.startNew.visibility = View.VISIBLE

                showWinner(winningLine)
            }
        }

    }


    private fun disableBoxes() {
        binding.one.isEnabled = false
        binding.two.isEnabled = false
        binding.three.isEnabled = false
        binding.four.isEnabled = false
        binding.five.isEnabled = false
        binding.six.isEnabled = false
        binding.seven.isEnabled = false
        binding.eight.isEnabled = false
        binding.nine.isEnabled = false
    }


    private fun showWinner(winningLine: WinningLine) {
        val (winningBoxes, background) = when (winningLine) {
            WinningLine.ROW_0 -> Pair(
                listOf(binding.one, binding.two, binding.three),
                R.drawable.horizontal_line
            )

            WinningLine.ROW_1 -> Pair(
                listOf(binding.four, binding.five, binding.six),
                R.drawable.horizontal_line
            )

            WinningLine.ROW_2 -> Pair(
                listOf(binding.seven, binding.eight, binding.nine),
                R.drawable.horizontal_line
            )

            WinningLine.COLUMN_0 -> Pair(
                listOf(binding.one, binding.four, binding.seven),
                R.drawable.vertical_line
            )

            WinningLine.COLUMN_1 -> Pair(
                listOf(binding.two, binding.five, binding.eight),
                R.drawable.vertical_line
            )

            WinningLine.COLUMN_2 -> Pair(
                listOf(binding.three, binding.six, binding.nine),
                R.drawable.vertical_line
            )

            WinningLine.DIAGONAL_LEFT -> Pair(
                listOf(binding.one, binding.five, binding.nine),
                R.drawable.left_diagonal_line
            )

            WinningLine.DIAGONAL_RIGHT -> Pair(
                listOf(binding.three, binding.five, binding.seven),
                R.drawable.right_diagonal_line
            )


        }

        winningBoxes.forEach { box ->
            box.background = ContextCompat.getDrawable(GameActivity@ this, background)
        }
    }
}
