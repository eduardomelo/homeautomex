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
    public class DatabaseContext : DbContext//, IDatabaseContext
        //where TEntidade : EntidadeBase<TChave>
    {


        static DatabaseContext()
        {
            System.Data.Entity.Database.SetInitializer<DatabaseContext>(null);
        }

        public DatabaseContext()
            : base("HomeAutomexConnection")
            //: base(@"data source=(localdb)\V11.0;initial catalog=HomeAutomexdb;integrated security=True;MultipleActiveResultSets=True;App=EntityFramework")
        {            
        }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Configurations.Add(new UsuarioMap());
            modelBuilder.Configurations.Add(new ResidenciaMap());
            modelBuilder.Configurations.Add(new ModuloMap());
            modelBuilder.Configurations.Add(new TipoPortaMap());
            modelBuilder.Configurations.Add(new PortaModuloMap());
            modelBuilder.Configurations.Add(new DispositivoMap());
            modelBuilder.Configurations.Add(new AmbienteMap());
            //modelBuilder.Configurations.Add(new DispositivoTesteMap());
                
            base.OnModelCreating(modelBuilder);
        }


        //public int SaveChanges()
        //{
        //    return this.SaveChanges();
        //}
    }
}
