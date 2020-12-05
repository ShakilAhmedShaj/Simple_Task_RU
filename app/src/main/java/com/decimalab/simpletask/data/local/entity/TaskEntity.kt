package com.decimalab.simpletask.data.local.entity


import android.os.Parcelable
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "task_entity")
@Parcelize
data class TaskEntity(
    /*@PrimaryKey(autoGenerate = true)
    var id: Int,*/

    @PrimaryKey
    @ColumnInfo(name = "task_id")
    @field:SerializedName("task_id")
    val taskId: Int,

    @ColumnInfo(name = "title")
    @field:SerializedName("title")
    val title: String,

    @ColumnInfo(name = "body")
    @field:SerializedName("body")
    val body: String?,

    @ColumnInfo(name = "priority")
    @field:SerializedName("priority")
    val priority: String,

    @ColumnInfo(name = "status")
    @field:SerializedName("status")
    val status: String?,

    @ColumnInfo(name = "user_id")
    @field:SerializedName("user_id")
    val userId: Int,

    @ColumnInfo(name = "updated_at")
    @field:SerializedName("updated_at")
    val updatedAt: String?,

    @ColumnInfo(name = "created_at")
    @field:SerializedName("created_at")
    val createdAt: String?,

    @ColumnInfo(name = "bg_color")
    @field:SerializedName("bg_color")
    var bgColor: Int

) : Parcelable {

    companion object {

        @JvmStatic
        @BindingAdapter("viewBackground")
        fun TextView.setBgColor(color: Int?) {
            if (color != null) {
                this.setBackgroundResource(color)
            }
        }
    }
}