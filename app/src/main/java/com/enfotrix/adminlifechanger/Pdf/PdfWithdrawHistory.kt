package com.enfotrix.adminlifechanger.Pdf


import com.enfotrix.adminlifechanger.Models.AgentWithdrawModel
import com.itextpdf.text.BaseColor
import com.itextpdf.text.Document
import com.itextpdf.text.Element
import com.itextpdf.text.FontFactory
import com.itextpdf.text.Paragraph
import com.itextpdf.text.Phrase
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Locale

class PdfWithdrawHistory(val agentWithdrawList: List<AgentWithdrawModel>) {
    fun generatePdf(outputStream: OutputStream): Boolean {
        val document = Document()
        try {
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, outputStream)
            document.open()
            val titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18f, BaseColor.BLACK)
            var title: Paragraph

            title = Paragraph("Withdraw History ", titleFont)


            title.alignment = Element.ALIGN_CENTER
            document.add(title)
            document.add(Paragraph("\n"))

            val table = PdfPTable(4)
            table.widthPercentage = 100f
            val headers = arrayOf(
                Paragraph("Request Balance", titleFont),
                Paragraph("Old Balance", titleFont),
                Paragraph("Reqeust Date", titleFont),
                Paragraph("Approval Date", titleFont)

            )
            for (header in headers) {
                val cell = PdfPCell(Phrase(header))
                cell.horizontalAlignment = Element.ALIGN_CENTER
                table.addCell(cell)
            }
            for (item in agentWithdrawList) {
                table.addCell(item.withdrawBalance)
                table.addCell(item.totalWithdrawBalance)
                table.addCell(SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(item.lastWithdrawReqDate.toDate()))
                table.addCell(item.withdrawApprovedDate?.toDate()
                    ?.let { SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(it) })
            }
            document.add(table)
            document.close()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
}
