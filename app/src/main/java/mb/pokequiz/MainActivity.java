package mb.pokequiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bradley.pokequiz.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText pokemon_guess = (EditText) findViewById(R.id.guessPoke);
        final TextView results = (TextView) findViewById(R.id.textView_Results);

        final String TAG = "MainActivity";
        pokemon_guess.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                String guess;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    guess = pokemon_guess.getText().toString();
                    Log.v(TAG, "String value = " + guess);
                    if (guess.equals("True")) {
                        // TODO : Use PokeWrapper to grab names and pictures
                        results.setText(guess);
                    }
                    else {
                        results.setText("False");
                    }
                    handled = true;
                }
                return handled;
            }
        });

    }

    
}

