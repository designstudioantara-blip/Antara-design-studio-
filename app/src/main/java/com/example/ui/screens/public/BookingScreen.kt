package com.example.ui.screens.public

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SquareFoot
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.ConsultationType
import com.example.ui.components.GoldButton
import com.example.ui.components.GoldOutlinedButton
import com.example.ui.components.LuxuryCard
import com.example.ui.components.LuxurySectionHeader
import com.example.ui.theme.CharcoalBorder
import com.example.ui.theme.CharcoalCard
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalElevated
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.MutedGrey
import com.example.ui.theme.PureWhite
import com.example.ui.theme.WarmGrey
import com.example.ui.viewmodel.MainViewModel

@Composable
fun BookingScreen(
  viewModel: MainViewModel,
  onNavigateToClientPortal: () -> Unit,
  modifier: Modifier = Modifier
) {
  val bookingSuccess by viewModel.bookingSuccessMessage.collectAsStateWithLifecycle()

  var selectedType by remember { mutableStateOf(ConsultationType.IN_STUDIO) }
  var selectedDate by remember { mutableStateOf("02 Mar 2025") }
  var selectedTimeSlot by remember { mutableStateOf("4:00 PM - 5:30 PM") }
  var clientName by remember { mutableStateOf("") }
  var clientPhone by remember { mutableStateOf("") }
  var clientEmail by remember { mutableStateOf("") }
  var siteAddress by remember { mutableStateOf("") }
  var spaceType by remember { mutableStateOf("3 BHK Luxury Residence") }
  var sqFt by remember { mutableStateOf("2400") }
  var budgetRange by remember { mutableStateOf("₹40 - ₹60 Lakhs") }
  var requirements by remember { mutableStateOf("") }

  val dates = listOf("02 Mar 2025", "03 Mar 2025", "04 Mar 2025", "05 Mar 2025", "06 Mar 2025", "07 Mar 2025")
  val slots = listOf("11:00 AM - 12:30 PM", "2:00 PM - 3:30 PM", "4:00 PM - 5:30 PM", "6:00 PM - 7:30 PM")
  val budgetOptions = listOf("₹25 - ₹40 Lakhs", "₹40 - ₹60 Lakhs", "₹60 - ₹90 Lakhs", "₹1+ Crore (Bespoke)")

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
        badgeText = "Private Appointment",
        title = "Schedule Design Consultation",
        subtitle = "Meet our Lead Architects in Surat or book an on-site spatial feasibility assessment."
      )
    }

    // Success Banner if submitted
    if (bookingSuccess != null) {
      item {
        LuxuryCard(hasGoldAccent = true) {
          Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = EmeraldSuccess,
                modifier = Modifier.size(24.dp)
              )
              Spacer(modifier = Modifier.width(10.dp))
              Text(
                text = "Appointment Confirmed!",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = PureWhite
              )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = bookingSuccess!!,
              fontFamily = FontFamily.SansSerif,
              fontSize = 12.sp,
              lineHeight = 17.sp,
              color = WarmGrey
            )
            Spacer(modifier = Modifier.height(12.dp))
            GoldOutlinedButton(
              text = "Book Another Consultation",
              onClick = { viewModel.clearBookingMessage() },
              modifier = Modifier.fillMaxWidth()
            )
          }
        }
      }
    }

    // 1. Consultation Format
    item {
      Column {
        Text(
          text = "1. SELECT CONSULTATION FORMAT",
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Bold,
          fontSize = 11.sp,
          letterSpacing = 1.sp,
          color = GoldLight
        )
        Spacer(modifier = Modifier.height(8.dp))

        ConsultationType.values().forEach { type ->
          val isSelected = selectedType == type
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 4.dp)
              .clip(RoundedCornerShape(8.dp))
              .background(if (isSelected) CharcoalElevated else CharcoalCard)
              .border(
                0.8.dp,
                if (isSelected) GoldPrimary else CharcoalBorder,
                RoundedCornerShape(8.dp)
              )
              .clickable { selectedType = type }
              .testTag("type_${type.name.lowercase()}")
              .padding(12.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = when (type) {
                  ConsultationType.IN_STUDIO -> Icons.Default.Business
                  ConsultationType.ON_SITE -> Icons.Default.LocationOn
                  ConsultationType.VIRTUAL_3D -> Icons.Default.Home
                },
                contentDescription = null,
                tint = if (isSelected) GoldPrimary else MutedGrey,
                modifier = Modifier.size(20.dp)
              )
              Spacer(modifier = Modifier.width(10.dp))
              Column {
                Text(
                  text = type.title,
                  fontFamily = FontFamily.Serif,
                  fontWeight = FontWeight.Bold,
                  fontSize = 13.5.sp,
                  color = PureWhite
                )
                Text(
                  text = type.locationText,
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

    // 2. Select Date & Slot
    item {
      LuxuryCard(hasGoldAccent = false) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "2. PREFERRED DATE & TIME SLOT",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 1.sp,
            color = GoldLight
          )
          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = "Select Date:",
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            color = MutedGrey
          )
          Spacer(modifier = Modifier.height(6.dp))
          LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(dates) { d ->
              val isSelected = selectedDate == d
              Surface(
                shape = RoundedCornerShape(8.dp),
                color = if (isSelected) GoldPrimary else CharcoalElevated,
                border = BorderStroke(0.6.dp, if (isSelected) GoldLight else CharcoalBorder),
                modifier = Modifier
                  .clickable { selectedDate = d }
                  .testTag("date_chip_$d")
              ) {
                Text(
                  text = d,
                  fontFamily = FontFamily.SansSerif,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                  fontSize = 11.5.sp,
                  color = if (isSelected) CharcoalDark else PureWhite,
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(14.dp))
          Text(
            text = "Select Time Slot:",
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            color = MutedGrey
          )
          Spacer(modifier = Modifier.height(6.dp))
          LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(slots) { s ->
              val isSelected = selectedTimeSlot == s
              Surface(
                shape = RoundedCornerShape(8.dp),
                color = if (isSelected) GoldPrimary else CharcoalElevated,
                border = BorderStroke(0.6.dp, if (isSelected) GoldLight else CharcoalBorder),
                modifier = Modifier
                  .clickable { selectedTimeSlot = s }
                  .testTag("slot_chip_$s")
              ) {
                Text(
                  text = s,
                  fontFamily = FontFamily.SansSerif,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                  fontSize = 11.5.sp,
                  color = if (isSelected) CharcoalDark else PureWhite,
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
              }
            }
          }
        }
      }
    }

    // 3. Client & Project Details
    item {
      LuxuryCard(hasGoldAccent = false) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "3. CONTACT & PROPERTY DETAILS",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 1.sp,
            color = GoldLight
          )
          Spacer(modifier = Modifier.height(12.dp))

          LuxuryTextField(
            value = clientName,
            onValueChange = { clientName = it },
            label = "Your Full Name / Business Name *",
            icon = Icons.Default.Person,
            tag = "input_client_name"
          )
          Spacer(modifier = Modifier.height(10.dp))

          Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            LuxuryTextField(
              value = clientPhone,
              onValueChange = { clientPhone = it },
              label = "Phone Number *",
              icon = Icons.Default.Phone,
              keyboardType = KeyboardType.Phone,
              tag = "input_client_phone",
              modifier = Modifier.weight(1f)
            )
            LuxuryTextField(
              value = clientEmail,
              onValueChange = { clientEmail = it },
              label = "Email Address",
              icon = Icons.Default.Email,
              keyboardType = KeyboardType.Email,
              tag = "input_client_email",
              modifier = Modifier.weight(1f)
            )
          }

          Spacer(modifier = Modifier.height(10.dp))
          LuxuryTextField(
            value = siteAddress,
            onValueChange = { siteAddress = it },
            label = "Site Location (e.g. Althan, Vesu, Dumas, Surat)",
            icon = Icons.Default.LocationOn,
            tag = "input_site_address"
          )

          Spacer(modifier = Modifier.height(10.dp))
          Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            LuxuryTextField(
              value = spaceType,
              onValueChange = { spaceType = it },
              label = "Space Type",
              icon = Icons.Default.Home,
              tag = "input_space_type",
              modifier = Modifier.weight(1.2f)
            )
            LuxuryTextField(
              value = sqFt,
              onValueChange = { sqFt = it },
              label = "Carpet Area (Sq. Ft.)",
              icon = Icons.Default.SquareFoot,
              keyboardType = KeyboardType.Number,
              tag = "input_sqft",
              modifier = Modifier.weight(0.8f)
            )
          }

          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = "Estimated Budget Range:",
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            color = MutedGrey
          )
          Spacer(modifier = Modifier.height(6.dp))
          LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(budgetOptions) { opt ->
              val isSelected = budgetRange == opt
              Surface(
                shape = RoundedCornerShape(6.dp),
                color = if (isSelected) GoldPrimary else CharcoalElevated,
                modifier = Modifier.clickable { budgetRange = opt }
              ) {
                Text(
                  text = opt,
                  fontFamily = FontFamily.SansSerif,
                  fontSize = 11.sp,
                  color = if (isSelected) CharcoalDark else PureWhite,
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(12.dp))
          LuxuryTextField(
            value = requirements,
            onValueChange = { requirements = it },
            label = "Special Requirements / Design Vision",
            icon = Icons.Default.Schedule,
            tag = "input_requirements"
          )
        }
      }
    }

    // Submit Button
    item {
      GoldButton(
        text = "Confirm Consultation Booking",
        onClick = {
          val nameToUse = if (clientName.isBlank()) "Patron Guest" else clientName
          val phoneToUse = if (clientPhone.isBlank()) "09725314309" else clientPhone
          val emailToUse = if (clientEmail.isBlank()) "client@antara.in" else clientEmail
          val sqFtVal = sqFt.toIntOrNull() ?: 2400
          viewModel.bookConsultation(
            name = nameToUse,
            phone = phoneToUse,
            email = emailToUse,
            date = selectedDate,
            timeSlot = selectedTimeSlot,
            type = selectedType,
            spaceType = spaceType,
            sqFt = sqFtVal,
            budgetRange = budgetRange,
            address = if (siteAddress.isBlank()) "Surat, Gujarat" else siteAddress,
            requirements = if (requirements.isBlank()) "Complete turnkey interior design & 3D renders" else requirements
          )
        },
        icon = Icons.Default.CalendarMonth,
        modifier = Modifier.fillMaxWidth()
      )
    }

    item { Spacer(modifier = Modifier.height(24.dp)) }
  }
}

@Composable
private fun LuxuryTextField(
  value: String,
  onValueChange: (String) -> Unit,
  label: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  modifier: Modifier = Modifier,
  keyboardType: KeyboardType = KeyboardType.Text,
  tag: String = ""
) {
  OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    label = { Text(text = label, fontFamily = FontFamily.SansSerif, fontSize = 12.sp) },
    leadingIcon = {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = GoldLight,
        modifier = Modifier.size(16.dp)
      )
    },
    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
    singleLine = true,
    shape = RoundedCornerShape(8.dp),
    colors = OutlinedTextFieldDefaults.colors(
      focusedBorderColor = GoldPrimary,
      unfocusedBorderColor = CharcoalBorder,
      focusedLabelColor = GoldLight,
      unfocusedLabelColor = MutedGrey,
      focusedTextColor = PureWhite,
      unfocusedTextColor = WarmGrey,
      focusedContainerColor = CharcoalElevated,
      unfocusedContainerColor = CharcoalDark
    ),
    modifier = modifier.fillMaxWidth().testTag(tag)
  )
}
