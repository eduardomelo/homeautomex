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
    public class UTDispositivoMap : EntityTypeConfiguration<UTDispositivo>
    {
        public UTDispositivoMap()
        {

            ToTable("UT_DISPOSITIVO");

            HasKey(e => e.Chave)
                .Property(e => e.Chave)
                .HasColumnName("CD_UT_DISPOSITIVO")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            Property(e => e.Cd_usuario).HasColumnName("ID_USUARIO").IsRequired();
            Property(e => e.Cd_dispositivo).HasColumnName("CD_DIPOSITIVO");
            Property(e => e.UT_utilizacao).HasColumnName("DT_ULTILIZACAO");
            Property(e => e.status).HasColumnName("STATUS");
        }

    }
}
