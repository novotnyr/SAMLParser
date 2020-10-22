# SAML Parser
A simple Java command-line utility to decrypt `EncryptedAssertion` objects in OpenSAML data.

## Requirements
 * Java
 * Maven

## Installation

```
mvn clean install
```

This will build the utility and download any dependencies that might be required.
A `target` folder will be created that has (among others) a JAR file with the suffix `jar-with-dependencies.jar`.
This is the command line utility.

## Usage

```
$ java -jar target/org.sakaiproject.Hilary.SAMLParser-1.0-SNAPSHOT-jar-with-dependencies.jar -h
Usage:
java -jar org.sakaiproject.Hilary.SAMLParser-1.0-SNAPSHOT-jar-with-dependencies.jar idpPublicKey spPublicKey spPrivateKey inputData inputEncoding outputEncoding

Parameters
 * idpPublicKey
    This is the Identity Provider's public key that can be used to verify signatures in SAML data.
 * spPublicKey
    This is your Service Provider's public key that the IdP uses to encrypt SAML data.
 * spPrivateKey
    This is your Service Provider's public key that can be used to decrypt SAML data.
 * inputData
    The SAML data that needs decrypting (if any.)
 * inputEncoding
    The encoding format that the inputData is passed in.
    Currently only supports 'base64' or 'plain', defaults to 'plain'.
 * outputEncoding
    The encoding format that the decrypted data should be outputted in.
    Currently only supports 'base64' or 'plain', defaults to 'plain'.

Examples:
    java -jar org.sakaiproject.Hilary.SAMLParser-1.0-SNAPSHOT-jar-with-dependencies.jar <idpPublicKey> <spPublicKey> <spPrivateKey> '<XML data>'
    java -jar org.sakaiproject.Hilary.SAMLParser-1.0-SNAPSHOT-jar-with-dependencies.jar <idpPublicKey> <spPublicKey> <spPrivateKey> '<base64-encoded XML data>' 'base64'
    java -jar org.sakaiproject.Hilary.SAMLParser-1.0-SNAPSHOT-jar-with-dependencies.jar <idpPublicKey> <spPublicKey> <spPrivateKey> '<base64-encoded XML data>' 'base64' 'base64'
```

## Alternative usage

The `FileBasedApp` class is used to launch the tool with parameters that read corresponding keys and data from files.

Please use `-cp` instead of `-jar` and use an explicit class name as an entrypoint.

```
$ java -cp target/org.sakaiproject.Hilary.SAMLParser-1.0-SNAPSHOT-jar-with-dependencies.jar org.sakaiproject.SAMLParser.FileBasedApp
Usage:
java -jar org.sakaiproject.Hilary.SAMLParser-1.0-SNAPSHOT-jar-with-dependencies.jar -i idpPublicKey -p spPublicKey -k spPrivateKey -I inputEncoding -O outputEncoding [-n] <XML data file>
```

Options:

 * `-i`: a path to a file with a Identity Provider's public key that can be used to verify signatures in SAML data.
 * `-p`: a path to a file with Service Provider's public key that the IdP uses to encrypt SAML data.
 * `-k`: a path to a file with a Service Provider's public key that can be used to decrypt SAML data.
 * `-I`: encoding format that the inputData is passed in. Currently only supports 'base64' or 'plain', defaults to 'plain'.
 * `-O`:  encoding format that the decrypted data should be outputted in. Currently only supports 'base64' or 'plain', defaults to 'plain'.
 * `-n`: whether to ignore SAML assertions without signatures
 * `XML data`: a path to the file with SAML data that needs decrypting (if any.)


## License

```
Copyright 2013 Apereo Foundation (AF) Licensed under the
Educational Community License, Version 2.0 (the "License"); you may
not use this file except in compliance with the License. You may
obtain a copy of the License at
 
      http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an "AS IS"
 BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 or implied. See the License for the specific language governing
 permissions and limitations under the License.
```
