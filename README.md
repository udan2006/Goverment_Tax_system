# JavaFX Government Tax System (JFXGTDS)

## 📝 Description

The **JavaFX Government Tax System (JFXGTDS)** is a user-friendly desktop application tailored for businesses and tax authorities to efficiently manage and process financial transaction data. The system enforces strict data validation, and provides real-time profit and tax calculation — all through a clean and interactive JavaFX GUI.

---

## ✨ Key Features

| Feature                          | Description                                                         |
|:---------------------------------|:--------------------------------------------------------------------|
| 📥 **CSV Import**                | Load financial transactions from properly formatted CSV files.      |
| 🛡️ **Validation**               | Automatic checksum verification for each transaction.               |
| ✏️ **Editing Popup**             | Instantly edit invalid transactions within the application.         |
| 📈 **Profit and Loss Analytics** | Real-time calculation of profit/loss per transaction.               |
| 💰 **Tax Computation**           | Apply custom tax rates and generate total tax.                      |
| 🧹 **Remove Transaction**        | Remove invalid records and zero profit records in transaction file  |

---

## 📷 Example Interaction

- Import transaction file: select the file using FileChooser
- View transaction file: view itemCode, salesPrice, internalPrice, quantity and checksum
- Validate each record: validate and show an alert total transaction, valid and invalid records
- Edit invalid records: go to the edit popup, update with new details and recalculate checksum
- Calculate Tax: show total profit, loss and get tax rate then show the total tax