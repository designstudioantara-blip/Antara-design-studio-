package com.example.ui.screens.admin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.ui.components.GoldButton
import com.example.ui.components.LuxuryCard
import com.example.ui.components.LuxurySectionHeader
import com.example.ui.components.StatusBadge
import com.example.ui.components.StatusBadgeType
import com.example.ui.theme.CharcoalBorder
import com.example.ui.theme.CharcoalCard
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalElevated
import com.example.ui.theme.CharcoalSurface
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.MutedGrey
import com.example.ui.theme.OffWhite
import com.example.ui.theme.PureWhite
import com.example.ui.theme.WarmGrey
import com.example.ui.viewmodel.MainViewModel

@Composable
fun AiDesignAdvisorScreen(
  viewModel: MainViewModel,
  modifier: Modifier = Modifier
) {
  val currentQuery by viewModel.aiQuery.collectAsStateWithLifecycle()
  val aiResponse by viewModel.aiResponse.collectAsStateWithLifecycle()
  val isGenerating by viewModel.isAiGenerating.collectAsStateWithLifecycle()

  var inputPrompt by remember { mutableStateOf("") }

  val samplePrompts = listOf(
    "Pair Italian Statuario marble with warm joinery",
    "Calculate lux lighting for 450 sq ft master bedroom",
    "Recommend veneer finishes for Surat coastal humidity",
    "Biophilic courtyard ventilation & lighting design"
  )

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(CharcoalDark)
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    item { Spacer(modifier = Modifier.height(6.dp)) }

    item {
      LuxurySectionHeader(
        badgeText = "Architectural Intelligence",
        title = "Antara Spatial AI & Material Concierge",
        subtitle = "Instant spatial reasoning, material compatibility pairings, and lighting calculations."
      )
    }

    // Quick Inspiration Prompt Chips
    item {
      Column {
        Text(
          text = "CURATED ARCHITECTURAL PROMPTS",
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Bold,
          fontSize = 10.5.sp,
          letterSpacing = 1.sp,
          color = GoldLight
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          items(samplePrompts) { prompt ->
            Surface(
              shape = RoundedCornerShape(20.dp),
              color = CharcoalElevated,
              border = BorderStroke(0.6.dp, CharcoalBorder),
              modifier = Modifier
                .clickable {
                  inputPrompt = prompt
                  viewModel.askAiAdvisor(prompt)
                }
                .testTag("prompt_chip_${prompt.take(6).lowercase()}")
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Default.AutoAwesome,
                  contentDescription = null,
                  tint = GoldLight,
                  modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = prompt,
                  fontFamily = FontFamily.SansSerif,
                  fontSize = 11.sp,
                  color = OffWhite
                )
              }
            }
          }
        }
      }
    }

    // Interactive Prompt Box
    item {
      LuxuryCard(hasGoldAccent = true) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "ASK ARCHITECTURAL CONCIERGE",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 1.sp,
            color = GoldLight
          )
          Spacer(modifier = Modifier.height(10.dp))

          OutlinedTextField(
            value = inputPrompt,
            onValueChange = { inputPrompt = it },
            placeholder = { Text("e.g. What stone complements smoked oak and brushed brass in a dining salon?", fontSize = 12.sp, color = MutedGrey) },
            minLines = 3,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = GoldPrimary,
              unfocusedBorderColor = CharcoalBorder,
              focusedTextColor = PureWhite,
              unfocusedTextColor = WarmGrey,
              focusedContainerColor = CharcoalElevated,
              unfocusedContainerColor = CharcoalDark
            ),
            modifier = Modifier.fillMaxWidth().testTag("ai_prompt_input")
          )

          Spacer(modifier = Modifier.height(12.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
          ) {
            GoldButton(
              text = if (isGenerating) "Synthesizing..." else "Generate Architectural Advice",
              onClick = {
                if (inputPrompt.isNotBlank() && !isGenerating) {
                  viewModel.askAiAdvisor(inputPrompt)
                }
              },
              icon = Icons.Default.AutoAwesome,
              modifier = Modifier.fillMaxWidth()
            )
          }
        }
      }
    }

    // AI Response Card
    if (isGenerating) {
      item {
        LuxuryCard(hasGoldAccent = false) {
          Row(
            modifier = Modifier.padding(20.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
          ) {
            CircularProgressIndicator(
              color = GoldPrimary,
              modifier = Modifier.size(24.dp),
              strokeWidth = 2.dp
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
              text = "Evaluating material science and architectural parameters...",
              fontFamily = FontFamily.SansSerif,
              fontSize = 12.sp,
              color = GoldLight
            )
          }
        }
      }
    } else if (aiResponse != null) {
      item {
        LuxuryCard(hasGoldAccent = true) {
          Column(modifier = Modifier.padding(18.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                  imageVector = Icons.Default.AutoAwesome,
                  contentDescription = null,
                  tint = GoldPrimary,
                  modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = "ARCHITECTURAL ANALYSIS",
                  fontFamily = FontFamily.SansSerif,
                  fontWeight = FontWeight.Bold,
                  fontSize = 11.sp,
                  letterSpacing = 1.sp,
                  color = GoldLight
                )
              }
              StatusBadge(text = "Antara AI", type = StatusBadgeType.GOLD)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = "Query: \"$currentQuery\"",
              fontFamily = FontFamily.SansSerif,
              fontWeight = FontWeight.SemiBold,
              fontSize = 12.sp,
              color = OffWhite
            )

            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(CharcoalBorder))
            Spacer(modifier = Modifier.height(10.dp))

            Text(
              text = aiResponse!!,
              fontFamily = FontFamily.SansSerif,
              fontSize = 12.5.sp,
              lineHeight = 19.sp,
              color = WarmGrey
            )
          }
        }
      }
    }

    item { Spacer(modifier = Modifier.height(24.dp)) }
  }
}
