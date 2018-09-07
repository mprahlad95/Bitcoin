Steps to run:

1. Run the make file using "make -f makefile"
2. Start the program using "java txblk"
3. You will initially start in non-interactive mode. Please press i/I for entering interactive mode.
4. The commands entered in full words are ignored for case. For example Interactive, interactive, interActive all result in the toggling of interactive/non-interactive mode.
5. The input UTXOs sum amount and output UTXOs sum amount are supposed to be equal for the scope of my code. This is because of the assumption that the last output UTXO is the commission and hence both the amounts should be equal. Else, the transaction was deemed bad.
6. All inputs must belong to the same user.

Steps to run grad wallet:

1. Start the program using "java wallet"
2. Wallet is built on the original ledger proram.
3. It has additional 2 functions which read private key files and sign transactions.

NOTES -

- For genesis transaction, signature won't be input as the transaction is not signed by the receiver.
- Read key file will take input for all usernames, even those who don't exist in the ledger.
- The public keys and private keys for testing have been provided along with their generated signatures for testing.
- KeyGenerator.java generates both Public and Private keys
- CreateSignaturesForMe.java generates the signature from the private key.

[Username - Keyfile, Keyfile, Signature]
[Alice - PublicKey, PrivateKey, AliceSignatureTxn1]
[Bob - PublicKey1, PrivateKey1, BobSignatureTxn2]
[Gopesh - PublicKey2, PrivateKey2, GopeshSignatureTxn3]

No identified bugs in the implementation.


PROBLEM -

Since the balance would be updated only after it's added to the block, the following transaction can only be added - 

1) Keyfile is read
2) The previous transaction is Checked and verified for signature
3) Output to the block. Then the balance will be updated.