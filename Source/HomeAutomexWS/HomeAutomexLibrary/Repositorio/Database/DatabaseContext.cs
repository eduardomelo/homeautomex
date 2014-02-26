using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.Entity;
using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio.Map;

namespace HomeAutomexLibrary.Repositorio.Database
{
    public class DatabaseContext : DbContext 
        //where TEntidade : EntidadeBase<TChave>
    {
        public DatabaseContext()
            : base(@"data source=(localdb)\V11.0;initial catalog=HomeAutomexdb;integrated security=True;MultipleActiveResultSets=True;App=EntityFramework")
        {            
        }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Configurations.Add(new UsuarioMap());

            base.OnModelCreating(modelBuilder);
        }
    }
}
