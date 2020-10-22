package org.sakaiproject.SAMLParser;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

public class FileBasedApp implements Callable<Integer> {
    public static final String PLAIN_ENCODING = "plain";

    @Option(names = {"-i", "--idp"},
            required = true,
            description = "Identity Provider's public key that can be used to verify signatures in SAML data")
    private File idpPublicKeyFile;

    @Option(names = {"-p", "--sp-pub"},
            required = true,
            description = "Service Provider's public key that the IdP uses to encrypt SAML data")
    private File spPublicKeyFile;

    @Option(names = {"-k", "--sp-key"},
            required = true,
            description = "Service Provider's public key that can be used to decrypt SAML data")
    private File spPrivateKeyFile;

    @Option(names = {"-I", "--input-enc"},
            defaultValue = PLAIN_ENCODING,
            description = "Encoding format that the inputData is passed in. Supports 'base64' or 'plain', defaults to 'plain.")
    private String inputEncoding;

    @Option(names = {"-O", "--output-enc"},
            defaultValue = PLAIN_ENCODING,
            description = "Encoding format that the decrypted data should be outputted in. Supports 'base64' or 'plain', defaults to 'plain'")
    private String outputEncoding;

    @Parameters(description = "The SAML data that needs decrypting (if any)")
    private File samlResponseFile;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new FileBasedApp()).execute(args);
        System.exit(exitCode);
    }

    public Integer call() throws Exception {
        List<String> args = new LinkedList<String>();
        args.add(IoUtils.loadAsString(idpPublicKeyFile));
        args.add(IoUtils.loadAsString(spPublicKeyFile));
        args.add(IoUtils.loadAsString(spPrivateKeyFile));
        args.add(IoUtils.loadAsString(samlResponseFile));

        if (!PLAIN_ENCODING.equals(this.inputEncoding)) {
            args.add(this.inputEncoding);
        }
        if (!PLAIN_ENCODING.equals(this.outputEncoding)) {
            args.add(this.outputEncoding);
        }

        System.out.println(args);

        App.main(args.toArray(new String[0]));
        return 0;
    }
}
