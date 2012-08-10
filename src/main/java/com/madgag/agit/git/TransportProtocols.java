package com.madgag.agit.git;

import static org.eclipse.jgit.transport.Transport.getTransportProtocols;

import org.eclipse.jgit.JGitText;
import org.eclipse.jgit.transport.TransportProtocol;
import org.eclipse.jgit.transport.URIish;

public class TransportProtocols {
    private static final String TAG = "TP";

    public static TransportProtocol protocolFor(URIish uri) {
        for (TransportProtocol p : getTransportProtocols()) {
            if (p.canHandle(uri))
                return p;
        }
        return null;
    }

    public static String niceProtocolNameFor(URIish uri) {
        TransportProtocol p = protocolFor(uri);
        if (p == null) {
            return null;
        }
        if (p.getSchemes().contains("file")) {
            return null;
        }
        String jGitProtocolName = p.getName();
        if (jGitProtocolName.equals(JGitText.get().transportProtoGitAnon)) {
            return "Git";
        }
        return jGitProtocolName;
    }
}
