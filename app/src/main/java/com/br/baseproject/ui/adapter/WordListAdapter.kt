package com.br.baseproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.br.baseproject.R
import com.br.baseproject.database.model.Registry

class WordListAdapter : ListAdapter<Registry, WordListAdapter.WordViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nome: TextView = itemView.findViewById(R.id.Nome)
        private val telefone: TextView = itemView.findViewById(R.id.Telefone)
        private val data: TextView = itemView.findViewById(R.id.data)

        fun bind(registry: Registry?) {
            nome.text = registry?.nome
            telefone.text = registry?.telefone
            data.text = registry?.dataCriacao
        }

        companion object {
            fun create(parent: ViewGroup): WordViewHolder {
                val view: View= LayoutInflater.from(parent.context).inflate(R.layout.adapter_item, parent, false)
                return WordViewHolder(view)
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<Registry>() {
        override fun areItemsTheSame(oldItem: Registry, newItem: Registry): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Registry, newItem: Registry): Boolean {
            return oldItem.nome == newItem.nome
        }
    }
}