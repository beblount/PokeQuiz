package mb.pokequiz.pokemon

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import mb.pokequiz.api.model.Pokemon

/**
 * Created by mbpeele on 4/12/17.
 */
class PokemonInfoAdapter(val pokemon: Pokemon) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        throw UnsupportedOperationException("not implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun getItemCount(): Int {
        throw UnsupportedOperationException("not implemented")
    }

    private class BasicInfoViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        init {

        }

        fun bind(pokemon: Pokemon) {

        }
    }

}