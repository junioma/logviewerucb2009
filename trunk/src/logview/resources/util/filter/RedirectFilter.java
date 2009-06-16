/*
 * Copyright 2003-2008 Universidade Católica de Brasília, Brasil
 *
 * Este arquivo é parte do JMBlogview.
 *
 * O JMBlogview é um software livre; você pode redistribui-lo e/ou modificá-lo
 * dentro dos termos da Licença Pública Geral GNU versão 2 como publicada
 * pela Fundação do Software Livre (FSF).
 *
 * Este programa é distribuido na esperança que possa ser util, mas SEM
 * NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU
 * para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o
 * título "Licenca.txt", junto com este programa, se não, escreva para a
 * Fundação do Software Livre(FSF) Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301, USA.
 */

package logview.resources.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet filter that ensures the FacesServlet is called before rendering
 * the page.  Most useful for getting JSP Faces pages to work correctly.
 * <p>
 * @version $Name:  $ ($Revision: adfrt/faces/adf-faces-demo/src/main/java/oracle/adfdemo/view/faces/webapp/RedirectFilter.java#1 $) $Date: 16-aug-2005.15:12:33 $
 * @author John Fallows
 */
public class RedirectFilter implements Filter 
{
  public static final String URL_PATTERN_PARAM = "faces-servlet-url-pattern";
  public static final String DEFAULT_URL_PATTERN = "/faces/*";

  public void init(
    FilterConfig filterConfig) throws ServletException
  {
    String pattern = filterConfig.getInitParameter(URL_PATTERN_PARAM);
    
    if (pattern == null)
      pattern = DEFAULT_URL_PATTERN;
      
    int offset = pattern.indexOf('*');
    if (offset > 1 && pattern.charAt(offset - 1) == '/')
      offset--;
      
    _servletPath = pattern.substring(0, offset);
  }

  public void destroy()
  {
    // Technically, we should dump _servletPath.  However,
    // OC4J is calling destroy(), then not calling init() before
    // using the Filter again!  So ignore destroy().
    //    _servletPath = null;
  }

  public void doFilter(
    ServletRequest  request, 
    ServletResponse response, 
    FilterChain     chain) throws IOException, ServletException
  {
    if (request instanceof HttpServletRequest)
    {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      String servletPath = httpRequest.getServletPath(); 
      if (!servletPath.startsWith(_servletPath))
      {
        servletPath = _servletPath + servletPath;
        String pathInfo = httpRequest.getPathInfo();
        String queryString = httpRequest.getQueryString();
        // Use a client-side redirect
        String url =
          (httpRequest.getContextPath() +
           servletPath + 
           (pathInfo == null ? "": pathInfo) +
           (queryString == null ? "": queryString));
        
        // Use a client-side redirect so that filters will run
        ((HttpServletResponse) response).sendRedirect(url);
        /*
          request.getRequestDispatcher(servletPath).
          forward(request, response);
        */
        return;
      }
    }

    chain.doFilter(request, response);
  }

  private String _servletPath;
}
