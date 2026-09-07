package com.example.ui.screens.client

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.ChatMessageEntity
import com.example.ui.components.GoldButton
import com.example.ui.components.LuxuryCard
import com.example.ui.components.StatusBadge
import com.example.ui.components.StatusBadgeType
import com.example.ui.theme.CharcoalBorder
import com.example.ui.theme.CharcoalCard
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalElevated
import com.example.ui.theme.CharcoalSurface
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.MutedGrey
import com.example.ui.theme.PureWhite
import com.example.ui.theme.WarmGrey
import com.example.ui.viewmodel.MainViewModel

@Composable
fun ChatScreen(
  viewModel: MainViewModel,
  onNavigateToMaterials: () -> Unit,
  modifier: Modifier = Modifier
) {
  val messages by viewModel.chatMessages.collectAsStateWithLifecycle()
  var inputMessage by remember { mutableStateOf("") }
  val listState = rememberLazyListState()

  LaunchedEffect(messages.size) {
    if (messages.isNotEmpty()) {
      listState.animateScrollToItem(messages.size - 1)
    }
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(CharcoalDark)
      .padding(horizontal = 16.dp)
  ) {
    Spacer(modifier = Modifier.height(6.dp))

    // Studio Active Chat Header
    LuxuryCard(hasGoldAccent = false) {
      Row(
        modifier = Modifier.padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(CharcoalElevated)
            .border(0.6.dp, GoldPrimary, CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Engineering,
            contentDescription = null,
            tint = GoldLight,
            modifier = Modifier.size(20.dp)
          )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = "Antara Architecture & PM Desk",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 13.5.sp,
            color = PureWhite
          )
          Text(
            text = "Ar. Hardik Patel • Er. Sunny Shah (Online)",
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            color = EmeraldSuccess
          )
        }
        StatusBadge(text = "The Althan Penthouse", type = StatusBadgeType.GOLD)
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Message Stream
    LazyColumn(
      state = listState,
      modifier = Modifier
        .weight(1f)
        .fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      items(messages) { msg ->
        ChatMessageBubble(
          message = msg,
          onActionClick = onNavigateToMaterials
        )
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Input Bar
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      OutlinedTextField(
        value = inputMessage,
        onValueChange = { inputMessage = it },
        placeholder = { Text("Message architect or site supervisor...", fontSize = 12.sp, color = MutedGrey) },
        shape = RoundedCornerShape(24.dp),
        colors = OutlinedTextFieldDefaults.colors(
          focusedBorderColor = GoldPrimary,
          unfocusedBorderColor = CharcoalBorder,
          focusedTextColor = PureWhite,
          unfocusedTextColor = WarmGrey,
          focusedContainerColor = CharcoalElevated,
          unfocusedContainerColor = CharcoalSurface
        ),
        modifier = Modifier
          .weight(1f)
          .testTag("chat_input")
      )
      Spacer(modifier = Modifier.width(8.dp))
      Box(
        modifier = Modifier
          .size(46.dp)
          .clip(CircleShape)
          .background(GoldPrimary)
          .testTag("chat_send_button"),
        contentAlignment = Alignment.Center
      ) {
        IconButton(
          onClick = {
            if (inputMessage.isNotBlank()) {
              viewModel.sendChatMessage(inputMessage)
              inputMessage = ""
            }
          }
        ) {
          Icon(
            imageVector = Icons.Default.Send,
            contentDescription = "Send Message",
            tint = CharcoalDark,
            modifier = Modifier.size(20.dp)
          )
        }
      }
    }
  }
}

@Composable
private fun ChatMessageBubble(
  message: ChatMessageEntity,
  onActionClick: () -> Unit
) {
  val isClient = message.isFromClient || message.senderRole.contains("Client", ignoreCase = true)

  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = if (isClient) Alignment.End else Alignment.Start
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = if (isClient) Arrangement.End else Arrangement.Start
    ) {
      Text(
        text = message.senderName,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        color = if (isClient) GoldLight else WarmGrey
      )
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = message.timestamp,
        fontFamily = FontFamily.SansSerif,
        fontSize = 10.sp,
        color = MutedGrey
      )
    }

    Spacer(modifier = Modifier.height(4.dp))

    Box(
      modifier = Modifier
        .clip(
          RoundedCornerShape(
            topStart = 12.dp,
            topEnd = 12.dp,
            bottomStart = if (isClient) 12.dp else 2.dp,
            bottomEnd = if (isClient) 2.dp else 12.dp
          )
        )
        .background(if (isClient) CharcoalElevated else CharcoalCard)
        .border(
          0.6.dp,
          if (isClient) GoldMuted else CharcoalBorder,
          RoundedCornerShape(12.dp)
        )
        .padding(12.dp)
    ) {
      Column {
        Text(
          text = message.messageText,
          fontFamily = FontFamily.SansSerif,
          fontSize = 12.5.sp,
          color = PureWhite,
          lineHeight = 17.sp
        )

        // Action Attachment Card (e.g. Swatch Approval)
        if (message.isActionableCard && message.actionTitle.isNotBlank()) {
          Spacer(modifier = Modifier.height(8.dp))
          Surface(
            color = CharcoalDark,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(0.6.dp, GoldPrimary),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(10.dp)) {
              Text(
                text = message.actionTitle,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = GoldLight
              )
              Spacer(modifier = Modifier.height(6.dp))
              GoldButton(
                text = "Open Material Studio",
                onClick = onActionClick,
                icon = Icons.Default.Check,
                modifier = Modifier.height(34.dp)
              )
            }
          }
        }
      }
    }
  }
}
