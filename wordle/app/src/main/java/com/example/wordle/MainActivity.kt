package com.example.wordle

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// My Imports
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ListView
import android.widget.Toast
import org.w3c.dom.Text
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View

class MainActivity : AppCompatActivity() {

    object FourLetterWordList {
        // List of most common 4 letter words from: https://7esl.com/4-letter-words/
        val fourLetterWords =
            "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn,Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang,Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make,Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save,Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want,Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast,Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male,Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore,Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever,Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,That,Then,This,Thus,Very,When,Wide"

        // Returns a list of four letter words as a list
        fun getAllFourLetterWords(): List<String> {
            return fourLetterWords.split(",")
        }

        // Returns a random four letter word from the list in all caps
        fun getRandomFourLetterWord(): String {
            val allWords = getAllFourLetterWords()
            val randomNumber = (0..allWords.size).shuffled().last()
            return allWords[randomNumber].uppercase()
        }
    }

    var wordToGuess: String = "AREA";
    // var wordToGuess: String = FourLetterWordList.getRandomFourLetterWord();

    private fun checkGuess(guess: String): String {
        var result: String = "";

        for (i in 0..3) {

            // Create a SpannableString
            val res: Char = guess[i];

            // right letter in the right place - GREEN
            if (guess[i] == wordToGuess[i]) {
                // Apply color to "green"

                /*result.append(res);
                result.setSpan(
                    ForegroundColorSpan(Color.GREEN), // Set the color to blue
                    i, // Start index of the word "blue"
                    i+1, // End index (exclusive)
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )*/

                result += "O"
            }

            // right letter in the wrong place - RED
            else if (guess[i] in wordToGuess) {
                // Apply color to "red"
                /*result.append(res);
                result.setSpan(
                    ForegroundColorSpan(Color.RED), // Set the color to red
                    i, // Start index of the word "red"
                    i+1, // End index (exclusive)
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )*/

                result += "+"
            }

            // wrong letter - GRAY
            else {
                // Apply color to "gray"
                /*result.append(res);
                result.setSpan(
                    ForegroundColorSpan(Color.GRAY), // Set the color to red
                    i, // Start index of the word "red"
                    i+1, // End index (exclusive)
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )*/

                result += "X"
            }
        }
        return result
    }

    private lateinit var arrayAdapter: ArrayAdapter<String>;
    private var itemList = mutableListOf<String>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val final_result: TextView = findViewById(R.id.result);
        val inputWord: EditText = findViewById(R.id.input_word);
        val submitButton: Button = findViewById(R.id.submit_button);
        val resultText: ListView = findViewById(R.id.result_text);
        var counter = 1;

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList);
        resultText.adapter = arrayAdapter;


        submitButton.setOnClickListener {
            val guess = inputWord.text.toString().uppercase();

            if (guess.length != 4) {
                Toast.makeText(this, "Enter a 4-letter word", Toast.LENGTH_SHORT).show()
            } else {
                // Inital Guess
                addItemToList("Guess #$counter - $guess")

                // Checked guess
                val result = checkGuess(guess);
                addItemToList("Guess #$counter Check - $result")

                // See if the client had won
                if (guess == wordToGuess) {
                    // addItemToList("Congrats!")
                    // addItemToList("Guess #$counter - $result")
                    Toast.makeText(this, "You Won!", Toast.LENGTH_LONG).show()
                    // wordToGuess = FourLetterWordList.getRandomFourLetterWord();
                    final_result.text = wordToGuess;
                    inputWord.visibility = View.INVISIBLE;
                    submitButton.visibility = View.INVISIBLE;
                }

                else if (counter >= 3) {
                    Toast.makeText(this, "You Lost!", Toast.LENGTH_LONG).show()
                    final_result.text = wordToGuess;
                    inputWord.visibility = View.INVISIBLE;
                    submitButton.visibility = View.INVISIBLE;
                }

                counter+=1;
            }

            inputWord.text.clear();
        }

    }

    private fun addItemToList(item: String) {
        itemList.add(item);
        arrayAdapter.notifyDataSetChanged()
    }


}










