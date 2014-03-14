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
            // Nome da maquina
            : base(@"data source=CST-EDSON;initial catalog=HomeAutomexdb;integrated security=True;MultipleActiveResultSets=True;App=EntityFramework")
        {            
        }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Configurations.Add(new UsuarioMap());
            modelBuilder.Configurations.Add(new ResidenciaMap());
            modelBuilder.Configurations.Add(new ModuloMap());
            modelBuilder.Configurations.Add(new AmbienteMap());
                
            base.OnModelCreating(modelBuilder);
        }
    }
}
