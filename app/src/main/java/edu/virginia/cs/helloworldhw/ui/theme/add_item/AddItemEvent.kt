package edu.virginia.cs.helloworldhw.ui.theme.add_item

sealed class AddItemEvent {
    data class onContentEdit(val name: String): AddItemEvent()
    data class onDueDateEdit(val dueDate: String): AddItemEvent()
    object onSave: AddItemEvent()

}