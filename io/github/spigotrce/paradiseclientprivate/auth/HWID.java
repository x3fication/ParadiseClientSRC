package io.github.spigotrce.paradiseclientprivate.auth;

public class HWID {
    private static final String osName = System.getProperty("os.name");
    private static final String osVersion = System.getProperty("os.version");
    private static final String osArch = System.getProperty("os.arch");
    private static final String userName = System.getProperty("user.name");
    private static final String systemRoot = System.getenv("SystemRoot");
    private static final String homeDrive = System.getenv("HOMEDRIVE");
    private static final String processorLevel = System.getenv("PROCESSOR_LEVEL");
    private static final String processorRevision = System.getenv("PROCESSOR_REVISION");
    private static final String processorIdentifier = System.getenv("PROCESSOR_IDENTIFIER");
    private static final String processorArchitecture = System.getenv("PROCESSOR_ARCHITECTURE");
    private static final String processorArchW6432 = System.getenv("PROCESSOR_ARCHITEW6432");
    private static final String numberOfProcessors = System.getenv("NUMBER_OF_PROCESSORS");

    public static String getHWID() {
        return Hasher.sha256Hash(osName + osVersion + osArch + userName + systemRoot + homeDrive + processorLevel + processorRevision + processorIdentifier + processorArchitecture + processorArchW6432 + numberOfProcessors);
    }
}
