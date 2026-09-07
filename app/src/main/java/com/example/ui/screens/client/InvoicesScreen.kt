package com.example.ui.screens.client

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.InvoiceEntity
import com.example.ui.components.GoldButton
import com.example.ui.components.GoldOutlinedButton
import com.example.ui.components.LuxuryCard
import com.example.ui.components.LuxurySectionHeader
import com.example.ui.components.StatusBadge
import com.example.ui.components.StatusBadgeType
import com.example.ui.theme.AmberPending
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
import com.example.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoicesScreen(
  viewModel: MainViewModel,
  modifier: Modifier = Modifier
) {
  val invoices by viewModel.invoices.collectAsStateWithLifecycle()
  val paymentMessage by viewModel.paymentSuccessMessage.collectAsStateWithLifecycle()
  var activeReceiptInvoice by remember { mutableStateOf<InvoiceEntity?>(null) }

  val totalPaid = invoices.filter { it.status == "Paid" }.sumOf { it.amountInRupees }
  val totalPending = invoices.filter { it.status != "Paid" }.sumOf { it.amountInRupees }

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
        badgeText = "Transparent Financials",
        title = "Milestone Billing & Invoices",
        subtitle = "Secure milestone cashflow schedule and verified GST invoices for The Althan Sky Penthouse."
      )
    }

    // Success banner if payment made
    if (paymentMessage != null) {
      item {
        LuxuryCard(hasGoldAccent = true) {
          Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = EmeraldSuccess,
                modifier = Modifier.size(22.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "Payment Verified & Processed!",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = PureWhite
              )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = paymentMessage!!,
              fontFamily = FontFamily.SansSerif,
              fontSize = 12.sp,
              color = WarmGrey
            )
            Spacer(modifier = Modifier.height(10.dp))
            GoldOutlinedButton(
              text = "Dismiss",
              onClick = { viewModel.clearPaymentMessage() },
              modifier = Modifier.fillMaxWidth()
            )
          }
        }
      }
    }

    // Financial Summary Strip
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        FinancialSummaryCard(
          title = "TOTAL SETTLED",
          amount = "₹${String.format("%,d", totalPaid)}",
          badge = "Verified",
          isSuccess = true,
          modifier = Modifier.weight(1f)
        )
        FinancialSummaryCard(
          title = "UPCOMING BALANCE",
          amount = "₹${String.format("%,d", totalPending)}",
          badge = "2 Milestones",
          isSuccess = false,
          modifier = Modifier.weight(1f)
        )
      }
    }

    // Invoices List
    items(invoices) { inv ->
      InvoiceRowCard(
        invoice = inv,
        onPayNow = { viewModel.payInvoice(inv.id, inv.amountInRupees, inv.milestoneTitle) },
        onViewReceipt = { activeReceiptInvoice = inv }
      )
    }

    item { Spacer(modifier = Modifier.height(24.dp)) }
  }

  // Digital Tax Invoice Modal
  if (activeReceiptInvoice != null) {
    val inv = activeReceiptInvoice!!
    BasicAlertDialog(
      onDismissRequest = { activeReceiptInvoice = null }
    ) {
      Surface(
        shape = RoundedCornerShape(16.dp),
        color = CharcoalSurface,
        border = BorderStroke(1.dp, GoldPrimary),
        modifier = Modifier.fillMaxWidth().padding(16.dp)
      ) {
        Column(modifier = Modifier.padding(20.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "TAX INVOICE RECEIPT",
              fontFamily = FontFamily.SansSerif,
              fontWeight = FontWeight.Bold,
              fontSize = 12.sp,
              letterSpacing = 1.sp,
              color = GoldLight
            )
            IconButton(
              onClick = { activeReceiptInvoice = null },
              modifier = Modifier.size(24.dp)
            ) {
              Icon(Icons.Default.Close, contentDescription = "Close", tint = GoldLight)
            }
          }

          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = "ANTARA DESIGN STUDIO",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = PureWhite
          )
          Text(
            text = "242, Times World, Althan, Surat, Gujarat 395017\nGSTIN: 24AAACA1234F1Z9",
            fontFamily = FontFamily.SansSerif,
            fontSize = 10.5.sp,
            lineHeight = 14.sp,
            color = MutedGrey
          )

          Spacer(modifier = Modifier.height(12.dp))
          Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(CharcoalBorder))
          Spacer(modifier = Modifier.height(12.dp))

          Text(
            text = "Billed To: Mr. Rajesh & Ananya Mehta\nSite: The Althan Sky Penthouse, Surat",
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.5.sp,
            color = WarmGrey
          )
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = "Invoice: ${inv.invoiceNumber}\nMilestone: ${inv.milestoneTitle}\nStatus: ${inv.status.uppercase()}",
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            color = GoldLight
          )

          if (inv.transactionRef.isNotBlank()) {
            Text(
              text = "Txn Ref: ${inv.transactionRef} (${inv.paidDate})",
              fontFamily = FontFamily.SansSerif,
              fontSize = 10.5.sp,
              color = EmeraldSuccess
            )
          }

          Spacer(modifier = Modifier.height(14.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "Total Amount Paid:",
              fontFamily = FontFamily.SansSerif,
              fontWeight = FontWeight.SemiBold,
              fontSize = 13.sp,
              color = PureWhite
            )
            Text(
              text = "₹${String.format("%,d", inv.amountInRupees)}",
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Bold,
              fontSize = 18.sp,
              color = GoldPrimary
            )
          }

          Spacer(modifier = Modifier.height(16.dp))
          GoldButton(
            text = "Download PDF Receipt",
            onClick = { activeReceiptInvoice = null },
            icon = Icons.Default.Download,
            modifier = Modifier.fillMaxWidth()
          )
        }
      }
    }
  }
}

@Composable
private fun FinancialSummaryCard(
  title: String,
  amount: String,
  badge: String,
  isSuccess: Boolean,
  modifier: Modifier = Modifier
) {
  LuxuryCard(modifier = modifier, hasGoldAccent = false) {
    Column(modifier = Modifier.padding(14.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = title,
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Bold,
          fontSize = 9.5.sp,
          letterSpacing = 1.sp,
          color = GoldMuted
        )
        StatusBadge(
          text = badge,
          type = if (isSuccess) StatusBadgeType.SUCCESS else StatusBadgeType.PENDING
        )
      }
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = amount,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = PureWhite
      )
    }
  }
}

@Composable
private fun InvoiceRowCard(
  invoice: InvoiceEntity,
  onPayNow: () -> Unit,
  onViewReceipt: () -> Unit
) {
  val isPaid = invoice.status == "Paid"
  val isPending = invoice.status == "Pending Approval"

  LuxuryCard(hasGoldAccent = isPending) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = invoice.invoiceNumber,
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Bold,
          fontSize = 11.sp,
          letterSpacing = 1.sp,
          color = GoldLight
        )
        StatusBadge(
          text = invoice.status,
          type = if (isPaid) StatusBadgeType.SUCCESS else if (isPending) StatusBadgeType.PENDING else StatusBadgeType.INFO
        )
      }

      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = invoice.milestoneTitle,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 14.5.sp,
        color = PureWhite
      )

      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = if (isPaid) "Settled on ${invoice.paidDate} (Ref: ${invoice.transactionRef})" else "Scheduled Due: ${invoice.dueDate}",
        fontFamily = FontFamily.SansSerif,
        fontSize = 11.sp,
        color = if (isPaid) EmeraldSuccess else MutedGrey
      )

      Spacer(modifier = Modifier.height(10.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Amount: ₹${String.format("%,d", invoice.amountInRupees)}",
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          fontSize = 16.sp,
          color = GoldPrimary
        )

        if (isPaid) {
          Surface(
            color = CharcoalElevated,
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(0.6.dp, CharcoalBorder),
            modifier = Modifier.clickable { onViewReceipt() }.testTag("receipt_${invoice.id}")
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = Icons.Default.Receipt,
                contentDescription = null,
                tint = GoldLight,
                modifier = Modifier.size(14.dp)
              )
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = "Tax Receipt",
                fontFamily = FontFamily.SansSerif,
                fontSize = 11.sp,
                color = PureWhite
              )
            }
          }
        } else if (isPending) {
          GoldButton(
            text = "Pay Milestone",
            onClick = onPayNow,
            icon = Icons.Default.Payment,
            modifier = Modifier.height(38.dp)
          )
        } else {
          Text(
            text = "Awaiting Stage",
            fontFamily = FontFamily.SansSerif,
            fontSize = 11.sp,
            color = MutedGrey
          )
        }
      }
    }
  }
}
