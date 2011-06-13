/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.Channel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rahul
 */
@Stateless
public class ChannelFacade implements ChannelFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Channel channel) {
        em.persist(channel);
    }

    public void edit(Channel channel) {
        em.merge(channel);
    }

    public void remove(Channel channel) {
        em.remove(em.merge(channel));
    }

    public Channel find(Object id) {
        return em.find(Channel.class, id);
    }

    public List<Channel> findAll() {
        return em.createQuery("select object(o) from CHANNEL_TABLE as o").getResultList();
    }
    public Channel findByChannelName(String channelName){
        if(!em.createQuery("select c from CHANNEL_TABLE c WHERE CHANNEL_NAME=?1").setParameter(1, channelName).getResultList().isEmpty())
           return (Channel) em.createQuery("select c from CHANNEL_TABLE c WHERE CHANNEL_NAME=?1").setParameter(1, channelName).getSingleResult();
        else
            return null;
    }
}
