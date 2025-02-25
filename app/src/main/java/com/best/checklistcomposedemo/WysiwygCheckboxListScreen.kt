package com.best.checklistcomposedemo
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WysiwygCheckboxListScreen() {
    var items by remember { mutableStateOf(emptyList<ListItem>()) }
    var nextId by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("WYSIWYG Checkbox List") },
                actions = {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                items = listOf(ListItem(nextId++, "New Item $nextId")) + items
                            }
                            .padding(8.dp),
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Item",
                        tint = Color.White
                    )
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            items(items, key = { it.id }) { item ->
                WysiwygCheckboxListItem(
                    item = item,
                    onRemove = { items = items.filter { it.id != item.id } }
                )
            }
        }
    }
}

@Composable
fun WysiwygCheckboxListItem(item: ListItem, onRemove: () -> Unit) {
    val richTextState = remember { RichTextState() }

    // Initialize text content
    LaunchedEffect(Unit) {
        richTextState.setHtml(item.text)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center
    ) {
        RichTextEditor(
            state = richTextState,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(8.dp)
        )

        // Rich Text Formatting Toolbar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ToolbarButton(Icons.Default.LocationOn) {
                richTextState.toggleSpanStyle(SpanStyle(fontWeight = FontWeight.Bold))
            }
            ToolbarButton(Icons.Default.LocationOn) {
                richTextState.toggleSpanStyle(SpanStyle(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic))
            }
            ToolbarButton(Icons.Default.LocationOn) {
                richTextState.toggleSpanStyle(SpanStyle(textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline))
            }
            ToolbarButton(Icons.Default.LocationOn) {
                richTextState.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Start))
            }
            ToolbarButton(Icons.Default.LocationOn) {
              //  richTextState.insertCheckbox()
            }
            ToolbarButton(Icons.Default.LocationOn) {
             //  richTextState.insertLink("https://example.com", "Click Here")
            }
            ToolbarButton(Icons.Default.Delete, onClick = onRemove)
        }
    }
}

@Composable
fun ToolbarButton(icon: ImageVector, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(imageVector = icon, contentDescription = null)
    }
}