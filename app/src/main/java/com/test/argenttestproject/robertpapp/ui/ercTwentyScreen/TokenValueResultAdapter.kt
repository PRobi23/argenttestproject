package com.test.argenttestproject.robertpapp.ui.ercTwentyScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.argenttestproject.R
import kotlinx.android.synthetic.main.token_value_result_item.view.*

class TokenValueResultAdapter(
    val arrayList: List<TokenValueResult>
) : RecyclerView.Adapter<TokenValueResultAdapter.NotesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TokenValueResultAdapter.NotesViewHolder {
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.token_value_result_item, parent, false)
        return NotesViewHolder(root)
    }

    override fun onBindViewHolder(holder: TokenValueResultAdapter.NotesViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    inner class NotesViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        fun bind(tokenValueResult: TokenValueResult) {
            binding.token_value_result_symbol_name.text = tokenValueResult.symbolName
            binding.token_value_result_value.text = tokenValueResult.balance.toString()
        }

    }

}
