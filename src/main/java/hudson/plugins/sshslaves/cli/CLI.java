package hudson.plugins.sshslaves.cli;
import hudson.model.Descriptor;
import hudson.model.Hudson;
import hudson.model.Node.Mode;
import hudson.plugins.sshslaves.SSHLauncher;
import hudson.slaves.DumbSlave;
import hudson.slaves.NodeProperty;
import hudson.slaves.RetentionStrategy;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jvnet.hudson.reactor.ReactorException;

class CLI {
    public static void main(String [ ] args)  {
        try {
            String root = "/tmp/hudson/";
            File f = new File(root);
            CLIServletContext s = new CLIServletContext();
            Hudson h = new Hudson(f,s);            
            SSHLauncher launcher = new SSHLauncher(
                    "localhost",
                    22,
                    "mdupont",
                    "mdupont123",
                    "-----BEGIN RSA PRIVATE KEY-----",
                    "def",
                    null);
            
            DumbSlave slave = new DumbSlave(
                    "Slave",
                    "Dummy",
                    "/tmp/test123",
                    "1",
                    Mode.NORMAL,
                    "Funky",
                    launcher,
                    RetentionStrategy.NOOP,
                    Collections.<NodeProperty<?>> emptyList());            
        } catch (IOException | InterruptedException | ReactorException | Descriptor.FormException ex) {
            Logger.getLogger(CLI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
