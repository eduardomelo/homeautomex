using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HomeAutomexLibrary.Entidade;

namespace HomeAutomexLibrary.Repositorio.Map
{
    public class DispositivoCenarioMap : EntityTypeConfiguration<DispositivoCenario>
    {
        public DispositivoCenarioMap()
        {

            ToTable("DISPOSITIVO_CENARIO");

            HasKey(e => e.Chave)
                .Property(e => e.Chave)
                .HasColumnName("CD_DISPOSITIVO_CENARIO")
                   .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            HasRequired(e => e.Dispositivos).WithOptional().Map(e => e.MapKey("CD_DISPOSITIVO"));
            HasRequired(e => e.Cenarios).WithOptional().Map(e => e.MapKey("CD_CENARIO"));
            Property(e => e.Status).HasColumnName("STATUS").IsRequired();





        }
    }
}
