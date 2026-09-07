package com.example.ui.screens.public

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.DesignStyle
import com.example.ui.components.GoldButton
import com.example.ui.components.GoldOutlinedButton
import com.example.ui.components.LuxuryCard
import com.example.ui.components.LuxurySectionHeader
import com.example.ui.components.StatusBadge
import com.example.ui.components.StatusBadgeType
import com.example.ui.theme.CharcoalBorder
import com.example.ui.theme.CharcoalCard
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalElevated
import com.example.ui.theme.CharcoalSurface
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldGradientEnd
import com.example.ui.theme.GoldGradientStart
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.MutedGrey
import com.example.ui.theme.OffWhite
import com.example.ui.theme.PureWhite
import com.example.ui.theme.WarmGrey
import com.example.ui.viewmodel.MainViewModel

data class QuizQuestion(
  val title: String,
  val subtitle: String,
  val options: List<QuizOption>
)

data class QuizOption(
  val label: String,
  val description: String,
  val styleHint: String
)

@Composable
fun StyleQuizScreen(
  viewModel: MainViewModel,
  onBookMatchedConsultation: () -> Unit,
  modifier: Modifier = Modifier
) {
  val answers by viewModel.quizAnswers.collectAsStateWithLifecycle()
  val matchedStyle by viewModel.matchedStyle.collectAsStateWithLifecycle()

  val questions = listOf(
    QuizQuestion(
      title = "1. Atmosphere & Lighting",
      subtitle = "How do you envision the ambient lighting and mood across your living areas?",
      options = listOf(
        QuizOption("Concealed Warm Coves & Monoliths", "Recessed 2700K ambient LED ribbons with dark marble reflections.", "Modern Luxury"),
        QuizOption("Grand Symmetry & Chandeliers", "Handcrafted crystal chandeliers, sconces, and gold leaf accents.", "Neo-Classical"),
        QuizOption("Organic Diffused Daylight", "Soft filtered sun rays through slatted teak louvers and linen sheer drapes.", "Warm Japandi"),
        QuizOption("Lush Greenery with Accent Downlights", "Cascading planter illumination and raw stone textural spotlighting.", "Biophilic Opulence")
      )
    ),
    QuizQuestion(
      title = "2. Core Materiality Preference",
      subtitle = "Which primary tactile surfaces resonate most with your aesthetic taste?",
      options = listOf(
        QuizOption("Italian Statuario & Brushed Bronze", "High-gloss bookmatched marble slabs and champagne metal trims.", "Modern Luxury"),
        QuizOption("Coffered Ceilings & Fluted Moldings", "White limestone trims, decorative wainscoting, and chevron oak.", "Neo-Classical"),
        QuizOption("Microcement, Teak & Rattan", "Natural raw textures, matte finishes, and earthy warmth.", "Warm Japandi"),
        QuizOption("Exposed Matte Steel & Fluted Glass", "Industrial elements elevated with cognac leather and amber tones.", "Industrial Chic")
      )
    ),
    QuizQuestion(
      title = "3. Spatial Layout & Flow",
      subtitle = "What is your philosophy on room zoning and furniture organization?",
      options = listOf(
        QuizOption("Seamless Open-Plan Salon", "Fluid conversation zones, curved sofas, and floating joinery.", "Modern Luxury"),
        QuizOption("Stately Formal Chambers", "Distinct formal drawing room, executive study, and grand dining.", "Neo-Classical"),
        QuizOption("Minimalist Zen Sanctuary", "Low-profile seating, uncluttered surfaces, and courtyard views.", "Warm Japandi"),
        QuizOption("Integrated Entertainment & Bar", "Monolithic quartz island, sunken lounge, and smart audio.", "Modern Luxury")
      )
    ),
    QuizQuestion(
      title = "4. Signature Color Mood",
      subtitle = "Which tonal palette reflects your personal sanctuary?",
      options = listOf(
        QuizOption("Moody Slate Charcoal & Champagne Gold", "Deep architectural contrasts with metallic warmth.", "Modern Luxury"),
        QuizOption("Ivory Pearl, Gold Leaf & Royal Blue", "Aristocratic elegance with opulent finishes.", "Neo-Classical"),
        QuizOption("Earthy Taupe, Sand & Olive Green", "Calming neutral serenity inspired by nature.", "Warm Japandi"),
        QuizOption("Obsidian Black, Cognac & Warm Brass", "Authoritative, sleek, and metropolitan.", "Industrial Chic")
      )
    )
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
        badgeText = "Aesthetic Discovery",
        title = "Design Style Matcher",
        subtitle = "Answer 4 curated design questions to unveil your personalized Antara architectural aesthetic."
      )
    }

    // Result Card if completed
    if (matchedStyle != null) {
      item {
        MatchedStyleResultCard(
          style = matchedStyle!!,
          onBookConsultation = onBookMatchedConsultation,
          onResetQuiz = { viewModel.resetQuiz() }
        )
      }
    } else {
      // Progress indicator
      item {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "QUESTIONS ANSWERED: ${answers.size} OF ${questions.size}",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 1.sp,
            color = GoldLight
          )
          Text(
            text = "${(answers.size * 25)}% Complete",
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            color = MutedGrey
          )
        }
      }
    }

    // Questions List
    items(questions.size) { qIndex ->
      val question = questions[qIndex]
      val selectedOption = answers[qIndex]

      LuxuryCard(hasGoldAccent = selectedOption != null) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = question.title,
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Bold,
              fontSize = 15.sp,
              color = PureWhite
            )
            if (selectedOption != null) {
              Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = EmeraldSuccess,
                modifier = Modifier.size(18.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = question.subtitle,
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.5.sp,
            color = MutedGrey
          )

          Spacer(modifier = Modifier.height(12.dp))

          question.options.forEachIndexed { oIndex, opt ->
            val isOptionSelected = selectedOption == oIndex
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                  if (isOptionSelected) CharcoalElevated else CharcoalDark
                )
                .border(
                  0.8.dp,
                  if (isOptionSelected) GoldPrimary else CharcoalBorder,
                  RoundedCornerShape(8.dp)
                )
                .clickable { viewModel.answerQuizQuestion(qIndex, oIndex) }
                .testTag("q_${qIndex}_opt_$oIndex")
                .padding(12.dp)
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                  modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(if (isOptionSelected) GoldPrimary else Color.Transparent)
                    .border(1.dp, GoldPrimary, CircleShape),
                  contentAlignment = Alignment.Center
                ) {
                  if (isOptionSelected) {
                    Box(
                      modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(CharcoalDark)
                    )
                  }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                  Text(
                    text = opt.label,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.5.sp,
                    color = if (isOptionSelected) GoldLight else PureWhite
                  )
                  Text(
                    text = opt.description,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 11.sp,
                    color = WarmGrey
                  )
                }
              }
            }
          }
        }
      }
    }

    item { Spacer(modifier = Modifier.height(24.dp)) }
  }
}

@Composable
private fun MatchedStyleResultCard(
  style: DesignStyle,
  onBookConsultation: () -> Unit,
  onResetQuiz: () -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(16.dp))
      .background(
        Brush.verticalGradient(
          listOf(CharcoalElevated, CharcoalCard, CharcoalSurface)
        )
      )
      .border(1.5.dp, GoldPrimary, RoundedCornerShape(16.dp))
      .padding(20.dp)
  ) {
    Column {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        StatusBadge(text = "YOUR DESIGN MATCH", type = StatusBadgeType.GOLD)
        Surface(
          shape = RoundedCornerShape(12.dp),
          color = CharcoalElevated,
          border = BorderStroke(0.6.dp, CharcoalBorder),
          modifier = Modifier.clickable { onResetQuiz() }
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              imageVector = Icons.Default.Refresh,
              contentDescription = "Retake quiz",
              tint = GoldLight,
              modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = "Retake",
              fontFamily = FontFamily.SansSerif,
              fontSize = 10.5.sp,
              color = PureWhite
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(12.dp))
      Text(
        text = style.title,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = PureWhite
      )
      Text(
        text = style.subtitle,
        fontFamily = FontFamily.SansSerif,
        fontSize = 12.5.sp,
        color = GoldLight
      )

      Spacer(modifier = Modifier.height(12.dp))
      Text(
        text = "SIGNATURE PALETTE & MATERIALITY:",
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        letterSpacing = 1.sp,
        color = GoldMuted
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = style.characteristics,
        fontFamily = FontFamily.SansSerif,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        color = WarmGrey
      )

      Spacer(modifier = Modifier.height(14.dp))
      Surface(
        color = CharcoalDark,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(0.6.dp, GoldMuted),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(10.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.AutoAwesome,
            contentDescription = null,
            tint = GoldPrimary,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Palette: ${style.paletteName}",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.5.sp,
            color = GoldLight
          )
        }
      }

      Spacer(modifier = Modifier.height(16.dp))
      GoldButton(
        text = "Book Studio Consultation for ${style.title}",
        onClick = onBookConsultation,
        icon = Icons.Default.CalendarMonth,
        modifier = Modifier.fillMaxWidth()
      )
    }
  }
}
