package decoration.scsowing.com.decoration.bindingadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import decoration.scsowing.com.decoration.R
import decoration.scsowing.com.decoration.Utils.LogUtils
import decoration.scsowing.com.decoration.databinding.ItemSecretDecorationBinding
import decoration.scsowing.com.decoration.model.DecorationSecretModel
import decoration.scsowing.com.decoration.ui.event.EventHandler


class SecretBindingAdapter(datas: MutableList<DecorationSecretModel>) : RecyclerView.Adapter<SecretBindingAdapter.ScretBindingHolder>() {



    val datas: MutableList<DecorationSecretModel> = mutableListOf()

    init {
        this.datas.addAll(datas)
    }

     fun update(datas: MutableList<DecorationSecretModel>){
        this.datas.clear()
        this.datas.addAll(datas)
        notifyDataSetChanged()
        LogUtils.e(this.javaClass.simpleName," update ");
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScretBindingHolder {
        val binding: ItemSecretDecorationBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_secret_decoration, parent, false)
        return ScretBindingHolder(binding)
    }

    override fun getItemCount(): Int {
        return datas?.size
    }

    override fun onBindViewHolder(holder: ScretBindingHolder, position: Int) {
        holder.bindData(datas.get(position),position)
    }


    class ScretBindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val TAG = this.javaClass.simpleName
        var dataBinding: ItemSecretDecorationBinding? = null

        constructor(viewBinder: ItemSecretDecorationBinding) : this(viewBinder.root) {
            dataBinding = viewBinder
        }

        fun bindData(dataItem: DecorationSecretModel,postion:Int) {
            dataBinding?.itemClick =object : EventHandler(dataBinding?.root?.context){
                override fun onClickView(view: View?) {
                    super.onClickView(view)
                    LogUtils.i(TAG," click item position $postion")
                }
            }
            dataBinding?.decorationSecretModel = dataItem
            dataBinding?.executePendingBindings()
        }
    }
}