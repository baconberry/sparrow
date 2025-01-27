package com.sparrowwallet.sparrow.io;

import com.sparrowwallet.drongo.protocol.ScriptType;
import com.sparrowwallet.drongo.wallet.Keystore;
import com.sparrowwallet.drongo.wallet.Wallet;
import com.sparrowwallet.drongo.wallet.WalletModel;

import java.io.InputStream;

public class CoboVaultMultisig extends ColdcardMultisig {
    @Override
    public String getName() {
        return "Cobo Vault Multisig";
    }

    @Override
    public WalletModel getWalletModel() {
        return WalletModel.COBO_VAULT;
    }

    @Override
    public Keystore getKeystore(ScriptType scriptType, InputStream inputStream, String password) throws ImportException {
        Keystore keystore = super.getKeystore(scriptType, inputStream, password);
        keystore.setLabel("Cobo Vault");
        keystore.setWalletModel(getWalletModel());

        return keystore;
    }

    @Override
    public String getKeystoreImportDescription(int account) {
        return "Import file or QR created by using the Multisig Wallet > ... > Show/Export XPUB feature on your Cobo Vault.";
    }

    @Override
    public Wallet importWallet(InputStream inputStream, String password) throws ImportException {
        Wallet wallet = super.importWallet(inputStream, password);
        for(Keystore keystore : wallet.getKeystores()) {
            keystore.setLabel(keystore.getLabel().replace("Coldcard", "Cobo Vault"));
            keystore.setWalletModel(WalletModel.COBO_VAULT);
        }

        return wallet;
    }

    @Override
    public String getWalletImportDescription() {
        return "Import file or QR created by using the Multisig Wallet > ... > Create Multisig Wallet feature on your Cobo Vault.";
    }

    @Override
    public String getWalletExportDescription() {
        return "Export file or QR that can be read by your Cobo Vault using the Multisig Wallet > ... > Import Multisig Wallet feature.";
    }

    @Override
    public boolean isWalletImportScannable() {
        return true;
    }

    @Override
    public boolean isKeystoreImportScannable() {
        return true;
    }

    @Override
    public boolean isWalletExportScannable() {
        return true;
    }
}
