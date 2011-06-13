/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Channel;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rahul
 */
@Remote
public interface ChannelFacadeRemote {

    void create(Channel channel);

    void edit(Channel channel);

    void remove(Channel channel);

    Channel find(Object id);

    List<Channel> findAll();

    public entity.Channel findByChannelName(java.lang.String channelName);

}
