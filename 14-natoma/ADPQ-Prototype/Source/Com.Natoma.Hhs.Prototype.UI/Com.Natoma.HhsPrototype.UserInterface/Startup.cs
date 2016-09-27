using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(Com.Natoma.HhsPrototype.UserInterface.Startup))]
namespace Com.Natoma.HhsPrototype.UserInterface
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            //ConfigureAuth(app);
        }
    }
}
