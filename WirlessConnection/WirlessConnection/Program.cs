using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.IO;

namespace WirlessConnection
{
    class Program
    {
        private static string Account = "";
        private static string Password = "";

        private static string HttpPost(string url, string postString)
        {
            // 要提交表单的URI字符串。
            //string url = "http://outofmemory.cn/code-snippet/";
            // 要提交的字符串数据。
            //string postString = "userName=test&Title=test&Content=test";
            // 初始化WebClient
            WebClient webClient = new WebClient();
            webClient.Headers.Add("Content-Type", "application/x-www-form-urlencoded");
            // 将字符串转换成字节数组
            byte[] postData = Encoding.ASCII.GetBytes(postString);
            //ASP.NET 返回的页面一般是Unicode,如果是简体中文应使用 
            //Encoding.GetEncoding("GB2312").GetBytes(postString)
            // 上传数据，返回页面的字节数组
            byte[] responseData = webClient.UploadData(url, "POST", postData);
            //ASP.NET 返回的页面一般是Unicode,如果是简体中文应使用 
            return Encoding.GetEncoding("GB2312").GetString(responseData);
            // 返回的将字节数组转换成字符串(HTML)
        }
        static void Main(string[] args)
        {
            try
            {
                WebClient client = new WebClient();
                string reply = client.DownloadString("http://www.baidu.com/");
                if (reply.IndexOf("Redirect") == -1)
                {
                    Console.Write("连接成功");
                }
                else
                {
                    throw new Exception();
                }
            }
            catch (Exception e)
            {
                Console.Write("无法连接");
                String sr = HttpPost("https://wac.shnu.edu.cn/login.html",
                        "buttonClicked=4&err_flag=0Z&info_flag=0&info_msg=0&username="
                                + Account + "&password=" + Password
                                + "&redirectUrl=www.baidu.com");
                while (sr.IndexOf("Password") == -1)
                {
                    sr = HttpPost("https://wac.shnu.edu.cn/login.html",
                            "buttonClicked=4&err_flag=0Z&info_flag=0&info_msg=0&username="
                                    + Account + "&password=" + Password
                                    + "&redirectUrl=www.baidu.com");
                }
                Console.Write("连接成功");
            }
        }
    }
}
        
