package com.example.ui.screens.public

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AntaraBrandBanner
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
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.MutedGrey
import com.example.ui.theme.OffWhite
import com.example.ui.theme.PureWhite
import com.example.ui.theme.WarmGrey

@Composable
fun ContactScreen(
  onBookAppointment: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current

  var inquiryName by remember { mutableStateOf("") }
  var inquiryPhone by remember { mutableStateOf("") }
  var inquiryMessage by remember { mutableStateOf("") }
  var inquirySent by remember { mutableStateOf(false) }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(CharcoalDark)
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    item { Spacer(modifier = Modifier.height(6.dp)) }

    item {
      AntaraBrandBanner(
        compact = false,
        showTagline = true
      )
    }

    item {
      LuxurySectionHeader(
        badgeText = "Studio Presence",
        title = "Connect with Antara",
        subtitle = "Experience our material archives and spatial mock-ups at our Times World studio in Surat."
      )
    }

    // Surat Studio Headquarters Card
    item {
      LuxuryCard(hasGoldAccent = true) {
        Column(modifier = Modifier.padding(18.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "SURAT DESIGN HEADQUARTERS",
              fontFamily = FontFamily.SansSerif,
              fontWeight = FontWeight.Bold,
              fontSize = 11.sp,
              letterSpacing = 1.2.sp,
              color = GoldLight
            )
            StatusBadge(text = "Experience Studio", type = StatusBadgeType.GOLD)
          }

          Spacer(modifier = Modifier.height(10.dp))
          Row(verticalAlignment = Alignment.Top) {
            Icon(
              imageVector = Icons.Default.LocationOn,
              contentDescription = null,
              tint = GoldPrimary,
              modifier = Modifier.size(20.dp).padding(top = 2.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
              Text(
                text = "Antara Design Studio",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = PureWhite
              )
              Text(
                text = "242, Times World, Althan,\nSurat, Gujarat 395017, India",
                fontFamily = FontFamily.SansSerif,
                fontSize = 12.5.sp,
                lineHeight = 18.sp,
                color = WarmGrey
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            GoldButton(
              text = "Call Direct",
              onClick = {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:09725314309"))
                context.startActivity(intent)
              },
              icon = Icons.Default.Call,
              modifier = Modifier.weight(1f)
            )
            GoldOutlinedButton(
              text = "Directions",
              onClick = {
                val mapUri = Uri.parse("geo:0,0?q=242+Times+World+Althan+Surat+Gujarat+395017")
                val intent = Intent(Intent.ACTION_VIEW, mapUri)
                context.startActivity(intent)
              },
              icon = Icons.Default.Navigation,
              modifier = Modifier.weight(1f)
            )
          }
        }
      }
    }

    // Direct Contact Channels List
    item {
      LuxuryCard(hasGoldAccent = false) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "DIRECT COMMUNICATION CHANNELS",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 1.sp,
            color = GoldLight
          )
          Spacer(modifier = Modifier.height(10.dp))

          ContactRowItem(
            icon = Icons.Default.Call,
            title = "Direct Studio Hotline",
            value = "09725314309 / +91 97253 14309",
            onClick = {
              val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:09725314309"))
              context.startActivity(intent)
            }
          )

          ContactRowItem(
            icon = Icons.Default.Email,
            title = "Official Studio Email",
            value = "designstudioantara@gmail.com",
            onClick = {
              val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:designstudioantara@gmail.com"))
              context.startActivity(intent)
            }
          )

          ContactRowItem(
            icon = Icons.Default.Language,
            title = "Official Website & Portfolio",
            value = "https://adsindia.org",
            onClick = {
              val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://adsindia.org/"))
              context.startActivity(intent)
            }
          )

          ContactRowItem(
            icon = Icons.Default.Schedule,
            title = "Studio Operating Hours",
            value = "Mon - Sat: 10:00 AM - 7:30 PM (Sun: By Appointment)",
            onClick = onBookAppointment
          )
        }
      }
    }

    // Social Links & Design Feeds
    item {
      LuxuryCard(hasGoldAccent = false) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "FOLLOW ANTARA ARCHITECTURAL REELS",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 1.sp,
            color = GoldLight
          )
          Spacer(modifier = Modifier.height(10.dp))

          val socials = listOf(
            Triple("Instagram", "@antaradesignstudio", "https://www.instagram.com/antaradesignstudio"),
            Triple("Facebook", "/antara309", "https://www.facebook.com/antara309"),
            Triple("Pinterest", "designstudioantara", "https://www.pinterest.com/designstudioantara"),
            Triple("YouTube", "@antaradesignstudio", "https://www.youtube.com/@antaradesignstudio")
          )

          socials.forEach { (platform, handle, url) ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .clickable {
                  val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                  context.startActivity(intent)
                }
                .padding(vertical = 6.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = platform,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = PureWhite
              )
              Text(
                text = handle,
                fontFamily = FontFamily.SansSerif,
                fontSize = 12.sp,
                color = GoldLight
              )
            }
          }
        }
      }
    }

    // Quick Inquiry Form
    item {
      LuxuryCard(hasGoldAccent = false) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "SEND A DIRECT INQUIRY",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 1.sp,
            color = GoldLight
          )
          Spacer(modifier = Modifier.height(10.dp))

          if (inquirySent) {
            Surface(
              color = CharcoalElevated,
              shape = RoundedCornerShape(8.dp),
              border = BorderStroke(0.6.dp, EmeraldSuccess),
              modifier = Modifier.fillMaxWidth()
            ) {
              Text(
                text = "Thank you! Your message has been routed to our Lead Architect. We will contact you shortly.",
                fontFamily = FontFamily.SansSerif,
                fontSize = 12.sp,
                color = EmeraldSuccess,
                modifier = Modifier.padding(12.dp)
              )
            }
          } else {
            OutlinedTextField(
              value = inquiryName,
              onValueChange = { inquiryName = it },
              label = { Text("Your Name", fontSize = 12.sp) },
              shape = RoundedCornerShape(8.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GoldPrimary,
                unfocusedBorderColor = CharcoalBorder,
                focusedTextColor = PureWhite,
                unfocusedTextColor = WarmGrey,
                focusedContainerColor = CharcoalElevated,
                unfocusedContainerColor = CharcoalDark
              ),
              modifier = Modifier.fillMaxWidth().testTag("inquiry_name_input")
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
              value = inquiryPhone,
              onValueChange = { inquiryPhone = it },
              label = { Text("Phone Number", fontSize = 12.sp) },
              shape = RoundedCornerShape(8.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GoldPrimary,
                unfocusedBorderColor = CharcoalBorder,
                focusedTextColor = PureWhite,
                unfocusedTextColor = WarmGrey,
                focusedContainerColor = CharcoalElevated,
                unfocusedContainerColor = CharcoalDark
              ),
              modifier = Modifier.fillMaxWidth().testTag("inquiry_phone_input")
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
              value = inquiryMessage,
              onValueChange = { inquiryMessage = it },
              label = { Text("Message / Project Location", fontSize = 12.sp) },
              minLines = 3,
              shape = RoundedCornerShape(8.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GoldPrimary,
                unfocusedBorderColor = CharcoalBorder,
                focusedTextColor = PureWhite,
                unfocusedTextColor = WarmGrey,
                focusedContainerColor = CharcoalElevated,
                unfocusedContainerColor = CharcoalDark
              ),
              modifier = Modifier.fillMaxWidth().testTag("inquiry_message_input")
            )
            Spacer(modifier = Modifier.height(12.dp))
            GoldButton(
              text = "Submit Inquiry",
              onClick = { inquirySent = true },
              icon = Icons.Default.Send,
              modifier = Modifier.fillMaxWidth()
            )
          }
        }
      }
    }

    item { Spacer(modifier = Modifier.height(24.dp)) }
  }
}

@Composable
private fun ContactRowItem(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  title: String,
  value: String,
  onClick: () -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onClick() }
      .padding(vertical = 8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(
      modifier = Modifier
        .size(32.dp)
        .clip(CircleShape)
        .background(CharcoalElevated),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = GoldLight,
        modifier = Modifier.size(16.dp)
      )
    }
    Spacer(modifier = Modifier.width(10.dp))
    Column {
      Text(
        text = title,
        fontFamily = FontFamily.SansSerif,
        fontSize = 11.sp,
        color = MutedGrey
      )
      Text(
        text = value,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 12.5.sp,
        color = PureWhite
      )
    }
  }
}
