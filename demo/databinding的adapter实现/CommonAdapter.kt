package decoration.scsowing.com.decoration.bindingadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import decoration.scsowing.com.decoration.R
import decoration.scsowing.com.decoration.Utils.LogUtils
import decoration.scsowing.com.decoration.databinding.ItemCaseDecorationBinding
import decoration.scsowing.com.decoration.model.DecorationCaseModel
import decoration.scsowing.com.decoration.ui.event.EventHandler

class CommonAdapter<T>() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mContext: Context? = null
    private var mDataList: MutableList<T>? = mutableListOf()
    private var layoutId = 0 //
    private var variableId = 0//

    constructor(context: Context, dataList: MutableList<T>, layoutId: Int, variableId: Int) : this() {
        this.mContext = context
        this.mDataList = dataList
        this.layoutId = layoutId
        this.variableId = variableId
    }

    fun update(datas: MutableList<T>) {
        mDataList!!.clear()
        mDataList!!.addAll(datas)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
        return CommonAdapter.CommViweHolder<T>(binding)
    }

    override fun getItemCount(): Int = mDataList!!.size!!


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder as CommViweHolder<T>
        holder.bindData(mDataList!!.get(position), position, variableId)
    }

    class CommViweHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val TAG = this.javaClass.simpleName
        var dataBinding: ViewDataBinding? = null

        constructor(binding: ViewDataBinding) : this(binding.root) {
            this.dataBinding = binding
        }

        fun bindData(dataItem: T, position: Int, variableId: Int) {
            LogUtils.i(TAG, " bindData $position")
            this.itemView.setOnClickListener { v: View ->
                LogUtils.i(TAG, " click item ${v.id} position $position")
            }
            dataBinding!!.setVariable(variableId, dataItem)
            dataBinding!!.executePendingBindings()
        }
    }
}